package com.intellij.advancedExpressionFolding

import com.intellij.ide.plugins.DynamicPluginListener
import com.intellij.ide.plugins.IdeaPluginDescriptor

private class PluginUnloadingListener : DynamicPluginListener {

    override fun beforePluginUnload(pluginDescriptor: IdeaPluginDescriptor, isUpdate: Boolean) = clearCache()

    override fun pluginUnloaded(pluginDescriptor: IdeaPluginDescriptor, isUpdate: Boolean) = clearCache()

    private fun clearCache() = FoldingService.get().clearAllKeys()
}
