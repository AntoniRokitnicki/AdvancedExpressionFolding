package com.intellij.advancedExpressionFolding.settings.view

import com.intellij.ui.JBColor
import com.intellij.util.ui.JBUI
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Font
import java.awt.LinearGradientPaint
import java.awt.RenderingHints
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.SwingConstants

internal class SettingsBannerPanel : JPanel(BorderLayout()) {
    private val gradientStart = JBColor(Color(0xF5E4FF), Color(0x1D1935))
    private val gradientEnd = JBColor(Color(0x9EE5FF), Color(0x123D72))
    private val borderColor = JBColor(Color(0xCDB7F6), Color(0x2E456F))
    private val titleColor = JBColor(Color(0x22124F), Color(0xE2E6FF))
    private val subtitleColor = JBColor(Color(0x3F2A75), Color(0xAFCFFF))

    init {
        isOpaque = false
        border = JBUI.Borders.empty(16, 20)

        add(
            JLabel("Advanced Expression Folding, but make it iconic.").apply {
                font = font.deriveFont(font.size2D + 4f).deriveFont(Font.BOLD)
                foreground = titleColor
                horizontalAlignment = SwingConstants.LEFT
            },
            BorderLayout.NORTH
        )

        add(
            JLabel("Bring polish back to your folds and keep Java feeling smooth.").apply {
                foreground = subtitleColor
                horizontalAlignment = SwingConstants.LEFT
            },
            BorderLayout.SOUTH
        )
    }

    override fun paintComponent(g: Graphics) {
        val g2 = g.create() as Graphics2D
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

        val arc = JBUI.scale(20)
        val gradient = LinearGradientPaint(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            floatArrayOf(0f, 1f),
            arrayOf(gradientStart, gradientEnd)
        )

        g2.paint = gradient
        g2.fillRoundRect(0, 0, width, height, arc, arc)

        g2.color = borderColor
        g2.drawRoundRect(0, 0, width - 1, height - 1, arc, arc)
        g2.dispose()

        super.paintComponent(g)
    }
}
