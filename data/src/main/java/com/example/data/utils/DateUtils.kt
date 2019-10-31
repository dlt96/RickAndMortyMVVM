package com.example.data.utils

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter


object DateUtils {

    fun stringToDate(dateString: String): LocalDate {
        val dateTimeFormatter : DateTimeFormatter = DateTimeFormatter.ISO_DATE
        return LocalDate.parse(dateString.substring(0,10), dateTimeFormatter)
    }
}