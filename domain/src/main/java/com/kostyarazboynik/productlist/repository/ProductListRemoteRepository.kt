package com.kostyarazboynik.productlist.repository

import com.kostyarazboynik.productlist.model.ProductListItem
import com.kostyarazboynik.productlist.model.UiState
import kotlinx.coroutines.flow.Flow

interface ProductListRemoteRepository {

    suspend fun getRemoteProductListItemsFlow(): Flow<UiState<List<ProductListItem>>>
}