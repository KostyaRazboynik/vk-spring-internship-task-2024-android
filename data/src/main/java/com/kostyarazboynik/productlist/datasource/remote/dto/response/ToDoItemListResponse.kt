package com.kostyarazboynik.productlist.datasource.remote.dto.response

import com.google.gson.annotations.SerializedName
import com.kostyarazboynik.productlist.datasource.remote.dto.model.ProductListItemNetworkEntity

data class ToDoItemListResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("revision")
    val revision: Int,
    @SerializedName("list")
    val list: List<ProductListItemNetworkEntity>
)
