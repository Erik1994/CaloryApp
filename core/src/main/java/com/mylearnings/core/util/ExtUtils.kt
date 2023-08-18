package com.mylearnings.core.util

fun <T> T?.orDefault(default: T) = this ?: default