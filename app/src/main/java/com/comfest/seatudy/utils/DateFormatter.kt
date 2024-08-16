package com.comfest.seatudy.utils

import java.text.SimpleDateFormat
import java.util.Locale

object DateFormatter {
    private val originalFormat by lazy {
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'",
            Locale.getDefault())
    }
    private val desiredFormat by lazy { SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()) }

    fun formatDate(dateString: String): String {
        val date = originalFormat.parse(dateString)
        return date?.let { desiredFormat.format(it) } ?: ""
    }
}