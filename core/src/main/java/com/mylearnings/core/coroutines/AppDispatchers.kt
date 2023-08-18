package com.mylearnings.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface AppDispatchers {
    val ioDispatcher: CoroutineDispatcher
    val mainDispatcher: CoroutineDispatcher
    val defaultDispatcher: CoroutineDispatcher
}

object AppDispatchersImpl : AppDispatchers {
    override val ioDispatcher: CoroutineDispatcher by lazy { Dispatchers.IO }
    override val mainDispatcher: CoroutineDispatcher by lazy { Dispatchers.Main.immediate }
    override val defaultDispatcher: CoroutineDispatcher by lazy { Dispatchers.Default }
}