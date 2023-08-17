package com.mylearnings.tracker_data.remote

import com.mylearnings.tracker_data.remote.constants.ApiConstants
import com.mylearnings.tracker_data.remote.dto.SearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenFoodApi {
    @GET(ApiConstants.SEARCH_URL)
     suspend fun searchFood(
         @Query("search_item") query: String,
         @Query("page") page: Int,
         @Query("page_size") pageSize: Int
     ): SearchDto
}