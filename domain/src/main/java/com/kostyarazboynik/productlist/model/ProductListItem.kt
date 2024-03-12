package com.kostyarazboynik.productlist.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
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
    val thumbnail: Uri,
    val images: List<Uri>,
) : Parcelable
