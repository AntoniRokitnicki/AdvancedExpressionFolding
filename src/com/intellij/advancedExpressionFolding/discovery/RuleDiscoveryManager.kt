package com.intellij.advancedExpressionFolding.discovery

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.DumbService
import com.intellij.openapi.project.Project
import com.intellij.util.concurrency.AppExecutorUtil
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicReference

@Service(Service.Level.PROJECT)
internal class RuleDiscoveryManager(private val project: Project) : Disposable {

    private val stateRef = AtomicReference<RuleDiscoveryReport?>(null)
    private val running = AtomicBoolean(false)
    private val scheduler = AppExecutorUtil.getAppScheduledExecutorService()
    private var scheduledFuture: ScheduledFuture<*>? = null

    init {
        ApplicationManager.getApplication().messageBus.connect(this)
            .subscribe(RuleDiscoveryToggleListener.TOPIC, RuleDiscoveryToggleListener { enabled ->
                if (enabled) {
                    scheduleScan()
                } else {
                    cancelScheduled()
                }
            })
    }

    fun initialize() {
        if (AdvancedExpressionFoldingSettings.getInstance().state.discoverNewFoldRules) {
            scheduleScan()
        }
    }

    fun currentReport(): RuleDiscoveryReport? = stateRef.get()

    private fun scheduleScan() {
        if (!running.compareAndSet(false, true)) {
            return
        }

        DumbService.getInstance(project).runWhenSmart {
            cancelScheduled(resetRunning = false)
            scheduledFuture = scheduler.schedule({
                if (!AdvancedExpressionFoldingSettings.getInstance().state.discoverNewFoldRules || project.isDisposed) {
                    running.set(false)
                    return@schedule
                }
                ProgressManager.getInstance().run(object : Task.Backgroundable(project, "Discover fold rules", true) {
                    override fun run(indicator: ProgressIndicator) {
                        executePipeline(indicator)
                    }

                    override fun onFinished() {
                        running.set(false)
                    }

                    override fun onCancel() {
                        running.set(false)
                    }
                })
            }, 5, TimeUnit.SECONDS)
        }
    }

    private fun executePipeline(indicator: ProgressIndicator) {
        val pipeline = RuleDiscoveryPipeline(project)
        val report = try {
            pipeline.execute(indicator)
        } catch (ignored: Throwable) {
            running.set(false)
            throw ignored
        }

        stateRef.set(report)
        RuleDiscoveryFileWriter.write(report)
        project.messageBus.syncPublisher(RuleDiscoveryListener.TOPIC).onRulesUpdated(report)
    }

    private fun cancelScheduled(resetRunning: Boolean = true) {
        scheduledFuture?.cancel(true)
        scheduledFuture = null
        if (resetRunning) {
            running.set(false)
        }
    }

    override fun dispose() {
        cancelScheduled()
    }
}
