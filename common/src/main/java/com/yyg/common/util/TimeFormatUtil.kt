package com.yyg.common.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * 时间格式
 *
 * @author lh
 */
object TimeFormatUtil {

    private val DEFAULT = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
    val DATE_HOUR = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA)
    val HOUR_MINUTE_SECOND = SimpleDateFormat("HH:mm:ss", Locale.CHINA)
    val HOUR_MINUTE = SimpleDateFormat("HH:mm", Locale.CHINA)
    val DATE = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
    val DATE_CHINA = SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA)
    val DATE_CHINA_HOUR = SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA)

    private val MONTH_DATE_HOUR_MINUTE = SimpleDateFormat("MM-dd HH:mm", Locale.CHINA)

    private const val JUST_NOW = "刚刚"
    private const val MIN = "分钟前"
    private const val TODAY = "今天"
    private const val YESTERDAY = "昨天"
    private const val THE_DAY_BEFORE_YESTERDAY = "前天"

    fun format(timeInMillis: Long): String {
        return format(timeInMillis, DEFAULT)
    }

    fun format(timeInMillis: Long, dateFormat: SimpleDateFormat): String {
        return dateFormat.format(Date(timeInMillis))
    }

    fun buildTimeString(millis: Long): String {
        val calendar: Calendar = Calendar.getInstance(Locale.CHINA)
        calendar.timeInMillis = millis
        val format = calendar.timeInMillis
        val now = System.currentTimeMillis()
        val nowCalendar: Calendar = Calendar.getInstance(Locale.CHINA)
        nowCalendar.timeInMillis = now

        val differ = now - format
        //  得到相差秒数
        val difSec = differ / 1000
        //  小于60秒
        if (difSec < 60) {
            return JUST_NOW
        }
        //得到相差分钟数
        val difMin = difSec / 60
        //  小于1小时
        if (difMin < 60) {
            return "$difMin$MIN"
        }
        //  得到相差小时
        val difHour = difMin / 60
        if (difHour < 24 && isSameDay(nowCalendar, calendar)) {
            return "$TODAY ${HOUR_MINUTE.format(calendar.time)}"
        }
        if (difHour < 48 && isYesterday(nowCalendar, calendar)) {
            return "$YESTERDAY ${HOUR_MINUTE.format(calendar.time)}"
        }
        val difDay = difHour / 24
        if (difDay < 31) {
            return when {
                isYesterday(nowCalendar, calendar) -> {
                    "$YESTERDAY ${HOUR_MINUTE.format(calendar.time)}"
                }
                isTheDayBeforeYesterday(nowCalendar, calendar) -> {
                    "$THE_DAY_BEFORE_YESTERDAY ${HOUR_MINUTE.format(calendar.time)}"
                }
                else -> {
                    MONTH_DATE_HOUR_MINUTE.format(calendar.time)
                }
            }
        }
        val difMonth = difDay / 31
        return if (difMonth < 12 && isSameYear(nowCalendar, calendar)) {
            MONTH_DATE_HOUR_MINUTE.format(calendar.time)
        } else DATE_HOUR.format(calendar.time)
    }

    private fun isSameYear(now: Calendar, msg: Calendar): Boolean {
        return calendarCompare(now, msg, Calendar.YEAR)
    }

    private fun isSameDay(now: Calendar, msg: Calendar): Boolean {
        return calendarCompare(now, msg, Calendar.DAY_OF_YEAR)
    }

    private fun isYesterday(now: Calendar, msg: Calendar): Boolean {
        return calendarCompare(now, msg, Calendar.DAY_OF_YEAR, 1)
    }

    private fun isTheDayBeforeYesterday(now: Calendar, msg: Calendar): Boolean {
        return calendarCompare(now, msg, Calendar.DAY_OF_YEAR, 2)
    }

    /**
     * 日期比较
     * @param now 当前时间
     * @param msg 需格式化时间
     * @param key 对比的维度,例如:天,年
     * @param difference 差值
     */
    private fun calendarCompare(
        now: Calendar,
        msg: Calendar,
        key: Int,
        difference: Int = 0
    ): Boolean {
        val nowDay = now[key]
        val msgDay = msg[key]
        return nowDay == msgDay + difference
    }


}