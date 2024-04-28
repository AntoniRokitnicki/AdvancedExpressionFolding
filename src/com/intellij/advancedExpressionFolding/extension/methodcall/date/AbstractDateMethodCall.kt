package com.intellij.advancedExpressionFolding.extension.methodcall.date

import com.intellij.advancedExpressionFolding.extension.methodcall.AbstractMethodCall

//TODO: refactor to date module
abstract class AbstractDateMethodCall : AbstractMethodCall() {

    override val classNames: List<String> by lazy { SHARED_CLASS_NAMES }

    companion object {
        @JvmStatic
        val SHARED_CLASS_NAMES = listOf(
            "java.time.LocalDate",
            "java.time.LocalDateTime",
            "java.time.LocalTime",
            "java.time.OffsetTime",
            "java.time.OffsetDateTime",
            "java.time.ZonedDateTime",
            "java.time.chrono.ChronoLocalDate",
            "java.time.chrono.ChronoLocalDateTime",
            "java.time.chrono.ChronoZonedDateTime",
            "java.time.chrono.HijrahDate",
            "java.time.chrono.JapaneseDate",
            "java.time.chrono.MinguoDate",
            "java.time.chrono.ThaiBuddhistDate",

            "org.joda.time.LocalDate",
            "org.joda.time.LocalDateTime",
            "org.joda.time.LocalTime",
            "org.joda.time.DateTime",
            "org.joda.time.Instant",
            //TODO: remove those after ignoring second arg in PrefixExpressionExt.MethodCallInformation.tryGet - isMethodSupported.test(className, methodName)
            // later Date/Calendar can be supported as well or maybe make isAfter/isBefore and after/before with 1 arg work for all classes
            "org.joda.time.base.AbstractDateTime",
            "org.joda.time.base.AbstractInstant",
            "org.joda.time.base.AbstractPartial",

            "java.util.Date",
            "java.sql.Date",
            "java.sql.Timestamp",
        )
    }
}