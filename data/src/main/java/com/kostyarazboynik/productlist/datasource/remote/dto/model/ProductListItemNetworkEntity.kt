package com.kostyarazboynik.productlist.datasource.remote.dto.model

import com.google.gson.annotations.SerializedName
import com.kostyarazboynik.productlist.model.Category
import com.kostyarazboynik.productlist.model.ProductListItem

data class ProductListItemNetworkEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("discountPercentage")
    val discountPercentage: Double,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("stock")
    val stock: Int,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("images")
    val images: List<String>,
) {
    fun networkEntityToModel(): ProductListItem =
        ProductListItem(
            id = this.id,
            title = this.title,
            description = this.description,
            price = this.price,
            discountPercentage = this.discountPercentage,
            rating = this.rating,
            stock = this.stock,
            brand = this.brand,
            category = Category.valueOf(this.category),
            thumbnail = this.thumbnail,
            images = this.images,
        )
}
