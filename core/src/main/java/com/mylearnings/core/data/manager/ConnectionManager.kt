package com.mylearnings.core.data.manager

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.mylearnings.core.R
import com.mylearnings.core.util.UiText

interface ConnectionManager {
    fun checkNetworkConnection(): Boolean
    fun getErrorMessage(e: Throwable): UiText
}

internal class ConnectionManagerImpl(private val context: Context) : ConnectionManager {

    override fun checkNetworkConnection(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
            else -> false
        }
    }

    override fun getErrorMessage(e: Throwable): UiText =
        if (checkNetworkConnection()) {
            UiText.DynamicString(e.message.orEmpty())
        } else {
            UiText.StringResource(R.string.internet_connection_error)
        }
}