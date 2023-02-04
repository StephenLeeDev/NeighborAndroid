package com.stephenleedev.neighborandroid.util.date

import org.joda.time.Days
import org.joda.time.LocalDateTime

/**
 * Written by StephenLeeDev on 2023/01/31.
 */

class DateUtil {

    // Date string format translator
    fun getDateStringByFormat(dateString: String, format: String = YYYY_MM_DD_KR): String =
        LocalDateTime.parse(dateString).toString(format)

    // Get Number of days between two dates
    fun getIntervalDaysFromBothDates(beginDate: LocalDateTime, endDate: LocalDateTime): Int {
        return Days.daysBetween(beginDate, endDate).days
    }

    /**
     * Less than a minute ago == a moment ago,
     * Less than an hour ago == 00 minutes ago,
     * Less than a day ago == yesterday,
     * else == YYYY_MM_DD
     *
     * 1분 미만 == 방금 전,
     * 1시간 미만 == 00분 전,
     * 1일 미만 == 어제,
     * else == YYYY_MM_DD
     */
    fun getIntervalTimeByString(dateString: String): String {
        val now = LocalDateTime.now().toDate().time
        val target = LocalDateTime.parse(dateString).toDate().time
        val intervalDate = now - target

        // 며칠전 메시지인지 일일 단위로 반환
        // 3일 전 == return 3
        val intervalByDays =
            getIntervalDaysFromBothDates(LocalDateTime.now(), LocalDateTime.parse(dateString))

        return when {
            (intervalDate < 1000 * 60) -> "방금 전"
            (intervalDate < 1000 * 60 * 60) -> "${intervalDate / (1000 * 60)}분 전"
            (intervalDate < 1000 * 60 * 60 * 24) -> "${intervalDate / (1000 * 60 * 60)}시간 전"
            (intervalDate < 1000 * 60 * 60 * 24 * 2) -> "어제"
            intervalByDays < 7 -> "${intervalDate / (1000 * 60 * 60 * 24)}일 전"
            intervalByDays < 365 -> getDateStringByFormat(format = MM_DD, dateString = dateString)
            else -> getDateStringByFormat(format = YYYY_MM_DD_KR, dateString = dateString)
        }
    }

    companion object {
        const val YYYY_MM_DD_E = "yyyy년 MM월 dd일 (E)"
        const val YYYY_MM_DD_E_DOT = "yyyy.MM.dd (E)"
        const val YYYY_MM_DD_KR = "yyyy년 MM월 dd일"
        const val YYYY_MM_DD = "yyyyMMdd"
        const val MM_DD = "MM월 dd일"
    }

}