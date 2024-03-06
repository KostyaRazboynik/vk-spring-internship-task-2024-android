package com.kostyarazboynik.productlist.model

sealed class UiState<out T> {
    data object Initial : UiState<Nothing>()
    data class Success<T>(val data: T, val message: String? = null) : UiState<T>()
    data class Error(val cause: String) : UiState<Nothing>()
}