/*
 * Copyright 2021 MohammadEsteki.ir
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libraries.utils.date

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.milliseconds

/**
 * Helper class for converting and presenting datetime format
 *
 * @author Mohammad Esteki
 */
object DateTimeHelper {

    private val timestampFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)

    /**
     * Format timestamp gregorian datetime for add request
     *
     * @param from Start gregorian datetime
     * @param to finish gregorian datetime
     * @return time
     */
    fun getDifferenceTime(from: String?, to: String?): String {
        val simpleFormat = SimpleDateFormat("dd/MM/yyyy  hh:mm a", Locale.ENGLISH)

        val beginCalendar = Calendar.getInstance()
        val finishCalendar = Calendar.getInstance()

        return try {
            beginCalendar.time = simpleFormat.parse(from!!)!!
            finishCalendar.time = simpleFormat.parse(to!!)!!

            val minutes = minutesDiff(beginCalendar, finishCalendar)
            val hours = hoursDiff(beginCalendar, finishCalendar)

            val hourMinute = hours * 60
            val resultMinute = minutes - hourMinute
            String.format(Locale.getDefault(), "%02d:%02d", hours, resultMinute)
        } catch (e: Exception) {
            e.printStackTrace()
            "00:00"
        }
    }

    @OptIn(ExperimentalTime::class)
    fun hoursDiff(c1: Calendar, c2: Calendar): Long {
        val diffInMillis = millisecondDiff(c1, c2)
        return Duration.milliseconds(diffInMillis).toDouble(DurationUnit.HOURS).toLong()
    }

    @OptIn(ExperimentalTime::class)
    fun minutesDiff(c1: Calendar, c2: Calendar): Long {
        val diffInMillis = millisecondDiff(c1, c2)
        return Duration.milliseconds(diffInMillis).toDouble(DurationUnit.MINUTES).toLong()
    }

    private fun millisecondDiff(c1: Calendar, c2: Calendar): Long = if (c1 > c2) {
        c1.timeInMillis - c2.timeInMillis
    } else {
        c2.timeInMillis - c1.timeInMillis
    }

    /**
     * Convert date fields to gregorian time
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @return Gregorian datetime
     */
    fun formatTime(year: Int, month: Int, day: Int, hour: Int, minute: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute)

        val hourFormat = SimpleDateFormat("hh:mm", Locale.ENGLISH)

        return hourFormat.format(calendar.time)
    }

    /**
     * Convert date fields to gregorian datetime
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @return Gregorian datetime
     */
    fun formatDateTime(year: Int, month: Int, day: Int, hour: Int, minute: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute)

        val slashDateTimeFormat = SimpleDateFormat("dd/MM/yyyy  hh:mm a", Locale.ENGLISH)

        return slashDateTimeFormat.format(calendar.time)
    }

    /**
     * Format timestamp gregorian datetime to time
     *
     * @param timeStamp Gregorian datetime
     * @return time
     */
    fun formatTimestampToTime(timeStamp: String?): String = try {
        val simpleFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH)
        val dateTimeStamp = simpleFormat.parse(timeStamp!!)!!
        val simpleTimeFormat = SimpleDateFormat("hh:mm a", Locale.ENGLISH)

        simpleTimeFormat.format(dateTimeStamp)
    } catch (e: Exception) {
        "00:00"
    }

    /**
     * Format timestamp gregorian datetime to timestamp
     *
     * @param customDateTime Gregorian datetime
     * @return time
     */
    fun formatDateTimeToTimeStamp(customDateTime: String): String = try {
        val customFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy  hh:mm a", Locale.ENGLISH)
        val customDate = customFormat.parse(customDateTime)!!

        timestampFormat.format(customDate)
    } catch (e: Exception) {
        "00:00"
    }

    /**
     * Format timestamp gregorian datetime for request list
     *
     * @param timeStamp Gregorian datetime
     * @return time
     */
    fun formatTimestampForRequest(timeStamp: String): String = try {
        val dateTimeStamp = timestampFormat.parse(timeStamp)!!

        val customFormat = SimpleDateFormat("E dd MMM hh:mm a", Locale.ENGLISH)

        customFormat.format(dateTimeStamp)
    } catch (e: Exception) {
        "00:00"
    }

    /**
     * Format timestamp gregorian datetime to visual time
     *
     * @param timeStamp Gregorian datetime
     * @return time
     */
    fun formatTimestampToVisualDateTime(timeStamp: String?): String = try {
        val simpleFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSS'Z'", Locale.ENGLISH)
        val dateTimeStamp = simpleFormat.parse(timeStamp!!)!!
        val simpleTimeFormat = SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH)

        simpleTimeFormat.format(dateTimeStamp)
    } catch (e: Exception) {
        "00/00/0000 00:00"
    }

    /**
     * Format timestamp gregorian datetime for request list
     *
     * @param timeStamp Gregorian datetime
     * @return time
     */
    fun formatDateTimeForChart(timeStamp: String): String = try {
        val timestampFormat = SimpleDateFormat("yyyy-MM", Locale.ENGLISH)

        val dateTimeStamp = timestampFormat.parse(timeStamp)!!

        val customFormat = SimpleDateFormat("MMM yy", Locale.ENGLISH)

        customFormat.format(dateTimeStamp)
    } catch (e: Exception) {
        "00:00"
    }

    /**
     * Format timestamp gregorian datetime for request list
     *
     * @param from Start gregorian datetime
     * @param to finish gregorian datetime
     * @return time
     */
    fun differenceTime(from: String, to: String): String = try {
        val beginCalendar = Calendar.getInstance()
        val finishCalendar = Calendar.getInstance()

        try {
            beginCalendar.time = timestampFormat.parse(from)!!
            finishCalendar.time = timestampFormat.parse(to)!!

            val minutes = minutesDiff(beginCalendar, finishCalendar)
            val hours = hoursDiff(beginCalendar, finishCalendar)

            val hourMinute = hours * 60
            val resultMinute = minutes - hourMinute
            String.format(Locale.getDefault(), "%01dh:%02dm", hours, resultMinute)
        } catch (e: ParseException) {
            e.printStackTrace()
            "00:00"
        }
    } catch (e: Exception) {
        "00:00"
    }

    /**
     * Get gregorian now custom format
     *
     * @return Gregorian custom format
     */
    fun nowGregDate(): String {
        val calendar = Calendar.getInstance()
        val simpleFormat = SimpleDateFormat("E . dd MMM . yyyy", Locale.ENGLISH)
        return simpleFormat.format(calendar.time)
    }

    /**
     * Format seconds to time example: 04:06
     *
     * @return Gregorian custom format
     */
    fun formatSecondToTime(second: Int): String {
        val hours = second / 60 / 60
        val totalMinutes = second / 60
        val hourMinutes = hours * 60
        val minutes = totalMinutes - hourMinutes
        return String.format(Locale.getDefault(), "%01d:%02d", hours, minutes)
    }

    /**
     * Format seconds to time example: 4h
     *
     * @return String time string
     */
    fun formatSecondToHour(second: Int): String {
        val hours = second / 60 / 60
        return String.format(Locale.getDefault(), "%d%s", hours, "h")
    }

    /**
     * Format seconds to time example: 40m
     *
     * @return String time string
     */
    fun formatSecondToMinute(second: Int): String {
        val minutes = second / 60
        return String.format(Locale.getDefault(), "%d%s", minutes, "m")
    }
}
