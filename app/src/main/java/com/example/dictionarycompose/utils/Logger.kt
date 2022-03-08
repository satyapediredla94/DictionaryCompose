package com.example.dictionarycompose.utils

import android.util.Log

object Logger {

    private const val isLoggingEnabled = true

    fun info(tag: String, message: String) {
        if (isLoggingEnabled) {
            Log.i(tag, message)
        }
    }

    fun error(tag: String, message: String) {
        if (isLoggingEnabled) {
            Log.e(tag, message)
        }
    }

    fun debug(tag: String, message: String) {
        if (isLoggingEnabled) {
            Log.d(tag, message)
        }
    }

    fun verbose(tag: String, message: String) {
        if (isLoggingEnabled) {
            Log.v(tag, message)
        }
    }

}