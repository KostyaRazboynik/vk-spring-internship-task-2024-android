package com.kostyarazboynik.productlist.datasource.remote.dto.response

import com.google.gson.annotations.SerializedName
import com.kostyarazboynik.productlist.model.ProductListItem

data class ToDoItemListResponse(
    @SerializedName("products")
    val list: List<ProductListItem>
)
