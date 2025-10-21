package com.intellij.advancedExpressionFolding.folding.util


import java.io.File

object GitUtils {

    class GitWrapper(private val dir: String = ".") {
        private fun runCommand(command: String): String {
            val process = ProcessBuilder(*command.split(" ").toTypedArray())
                .directory(File(dir))
                .redirectErrorStream(true)
                .start()

            val result = process.inputStream.bufferedReader().use { it.readText() }
            process.waitFor()
            return result
        }

        fun addAll() = println(runCommand("git add ."))

        fun commit(message: String) = println(runCommand("git commit -m $message"))
        fun commitMsgFile() = println(runCommand("git commit -F commit_message.txt"))


        fun removeChangesInFolder(folder: String) = println(runCommand("git checkout -- $folder"))
    }

    fun commitAllChanges(message: String? = null, dir: String = ".", cleanupDirs: String? = null) {
        GitWrapper(dir).run {
            cleanupDirs?.let {
                removeChangesInFolder(it)
            }
            addAll()
            message?.let {
                commit(it)
            } ?: commitMsgFile()
        }
    }


    @JvmStatic
    fun main(args: Array<String>) = commitAllChanges()

}
