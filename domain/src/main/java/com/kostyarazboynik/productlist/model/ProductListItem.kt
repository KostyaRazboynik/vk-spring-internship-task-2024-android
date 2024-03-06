package com.kostyarazboynik.productlist.model

data class ProductListItem(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val category: Category,
    val thumbnail: Any, // TODO picture
    val images: List<Any>, // TODO list of picture
)
