package com.mylearnings.tracker_data.remote.constants

object ApiConstants {
    const val BASE_URL = "https://us.openfoodfacts.org/"
    const val SEARCH_URL = "cgi/search.pl?search_simple=1&json=1&action=process&fields=product_name,nutriments,image_front_thumb_url"
}