package com.kostyarazboynik.productlist.model

import android.net.Uri

data class ProductListItemJson(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>,
) {
    fun toProductListItem(): ProductListItem =
        ProductListItem(
            id = this.id,
            title = this.title,
            description = this.description,
            price = this.price,
            discountPercentage = this.discountPercentage,
            rating = this.rating,
            stock = this.stock,
            brand = this.brand,
            category = this.category.toCategory(),
            thumbnail = Uri.parse(this.thumbnail),
            images = this.images.map { Uri.parse(it) }
        )
}