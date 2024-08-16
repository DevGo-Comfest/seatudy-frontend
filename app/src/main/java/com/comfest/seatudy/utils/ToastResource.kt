package com.comfest.seatudy.utils

import android.content.Context
import android.widget.Toast

object ToastResource {

    fun toastResource(message: String, context: Context) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        )
            .show()
    }
}