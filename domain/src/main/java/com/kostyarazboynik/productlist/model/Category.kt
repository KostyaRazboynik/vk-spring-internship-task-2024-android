package com.kostyarazboynik.productlist.model

enum class Category {
    SMARTPHONES,
    LAPTOPS,
    FRAGRANCES,
    SKINCARE,
    GROCERIES,
    HOME_DECORATIONS,
    UNKNOWN,
}

fun String.toCategory(): Category =
    try {
        Category.valueOf(this)
    } catch (e: IllegalArgumentException) {
        Category.UNKNOWN
    }
