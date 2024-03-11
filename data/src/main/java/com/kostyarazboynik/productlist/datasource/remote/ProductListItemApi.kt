package com.kostyarazboynik.productlist.datasource.remote

import com.kostyarazboynik.productlist.datasource.remote.dto.response.ToDoItemListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductListItemApi {

    @GET("products")
    suspend fun getList(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int,
    ): Response<ToDoItemListResponse>
}