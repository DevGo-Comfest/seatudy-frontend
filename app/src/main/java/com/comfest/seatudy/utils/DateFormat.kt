package com.comfest.seatudy.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormat {
    fun formatDate(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val date: Date? = inputFormat.parse(dateString)
        return date?.let { outputFormat.format(it) } ?: ""
    }
}