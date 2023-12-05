package com.rdissi.mytest.common

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

object DateFormatter {

    private val formatter = SimpleDateFormat("d MMM yyyy", Locale.FRENCH)

    fun formatLastViewTime(timestampSecond: Long): String {
        val now = Calendar.getInstance().timeInMillis
        val timestampMillis = TimeUnit.SECONDS.toMillis(timestampSecond)
        val elapsedTime = now - timestampMillis
        return when {
            elapsedTime < ONE_MINUTE_MILLIS -> "Now"
            elapsedTime < TWO_MINUTES_MILLIS -> "A minute ago"
            elapsedTime < ONE_HOUR_MILLIS -> "${elapsedTime / ONE_MINUTE_MILLIS} minutes ago"
            elapsedTime < TWO_HOURS_MILLIS -> "An hour ago"
            elapsedTime < ONE_DAY_MILLIS -> "${elapsedTime / ONE_HOUR_MILLIS} hours ago"
            elapsedTime < TWO_DAYS_MILLIS -> "Yesterday"
            elapsedTime < ONE_WEEK_MILLIS -> "${elapsedTime / ONE_DAY_MILLIS} days ago"
            else -> formatDate(timestampMillis)
        }
    }

    private fun formatDate(timestampMillis: Long): String {
        return formatter.format(Date(timestampMillis))
    }

    private val ONE_MINUTE_MILLIS = TimeUnit.MINUTES.toMillis(1)
    private val ONE_HOUR_MILLIS = TimeUnit.HOURS.toMillis(1)
    private val ONE_DAY_MILLIS = TimeUnit.DAYS.toMillis(1)
    private val TWO_MINUTES_MILLIS = TimeUnit.MINUTES.toMillis(2)
    private val TWO_HOURS_MILLIS = TimeUnit.HOURS.toMillis(2)
    private val TWO_DAYS_MILLIS = TimeUnit.DAYS.toMillis(2)
    private val ONE_WEEK_MILLIS = 7 * TimeUnit.DAYS.toMillis(1)

}
