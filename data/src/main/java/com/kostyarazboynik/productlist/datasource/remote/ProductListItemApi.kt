package com.kostyarazboynik.productlist.datasource.remote

import com.kostyarazboynik.productlist.datasource.remote.dto.response.ToDoItemListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductListItemApi {

    @GET("list")
    suspend fun getList(): Response<ToDoItemListResponse>

}