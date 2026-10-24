package org.androidgraal.common

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

fun timestamp(): String =
    OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)

fun secondsSince(startedAt: Long): String = "%.1f".format(Locale.ROOT, (System.nanoTime() - startedAt) / 1_000_000_000.0)
