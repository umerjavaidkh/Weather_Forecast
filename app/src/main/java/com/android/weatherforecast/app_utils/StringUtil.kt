package com.android.weatherforecast.app_utils

fun String.capitalizeWords(): String = split(" ").map { it.capitalize() }.joinToString(" ")