package com.mylearnings.core_ui.util

class ItemHolder<I>(
    private val itemProvider: suspend () -> I
) {

    private var item: I? = null

    suspend fun getItem() = item ?: itemProvider().also { item = it }
}