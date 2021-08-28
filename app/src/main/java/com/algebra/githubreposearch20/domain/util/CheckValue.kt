package com.algebra.githubreposearch20.domain.util

import com.algebra.githubreposearch20.App
import com.algebra.githubreposearch20.R

fun parseDate(date: String?): String {
    return try {
        date?.let {
            val month =
                date.substring(date.indexOf('-') + 1, date.indexOf('-', date.indexOf('-') + 1))
            val year = date.substring(0, date.indexOf('-'))
            val day =
                date.substring(date.indexOf('-', date.indexOf('-') + 1) + 1, date.indexOf('T'))
            "$day.$month.$year"
        } ?: App.getStringResource(R.string.check_value_message)
    } catch (e: Exception) {
        App.getStringResource(R.string.check_value_message)
    }
}

fun checkValue(value: String?, length: Int = 12): String =
    try {
        value?.let {
            if (it.isBlank() || value == "null") App.getStringResource(R.string.check_value_message)
            else if (it.length > length) "${it.substring(0, length - 2)}..."
            else it
        } ?: App.getStringResource(R.string.check_value_message)
    } catch (e: Exception) {
        App.getStringResource(R.string.check_value_message)
    }

fun checkIfNull(value: Int?): Int =
    try {
        value?.toString()
        value?.let { value } ?: 0
    } catch (e: Exception) {
        0
    }

fun checkURL(value: String?): String =
    try {
        value?.let { value } ?: ""
    } catch (e: Exception) {
        ""
    }
