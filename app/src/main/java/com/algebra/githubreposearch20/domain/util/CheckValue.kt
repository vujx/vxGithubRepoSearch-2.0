package com.algebra.githubreposearch20.domain.util

import com.algebra.githubreposearch20.App
import com.algebra.githubreposearch20.R

fun parseDate(date: String): String {
    return try {
        val month = date.substring(date.indexOf('-') + 1, date.indexOf('-', date.indexOf('-') + 1))
        val year = date.substring(0, date.indexOf('-'))
        val day = date.substring(date.indexOf('-', date.indexOf('-') + 1) + 1, date.indexOf('T'))
        "$day.$month.$year"
    } catch (e: Exception) {
        App.getStringResource(R.string.check_value_message)
    }
}

fun checkValue(value: String, length: Int = 12): String =
    try {
        if (value.isBlank() || value == "null") App.getStringResource(R.string.check_value_message)
        else if (value.length > length) "${value.substring(0, length - 2)}..."
        else value
    } catch (e: Exception) {
        App.getStringResource(R.string.check_value_message)
    }
