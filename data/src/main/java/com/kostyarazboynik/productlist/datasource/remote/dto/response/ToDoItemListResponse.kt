package com.kostyarazboynik.productlist.datasource.remote.dto.response

import com.google.gson.annotations.SerializedName
import com.kostyarazboynik.productlist.model.ProductListItemJson

data class ToDoItemListResponse(
    @SerializedName("products")
    val list: List<ProductListItemJson>
)
