package com.example.data.utils

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter


object DateUtils {

    fun stringToDate(dateString: String): LocalDate {
        val dateTimeFormatter : DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
        return LocalDate.parse(dateString, dateTimeFormatter)
    }
}