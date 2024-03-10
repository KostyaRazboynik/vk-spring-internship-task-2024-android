package com.kostyarazboynik.productlist.repository

import com.kostyarazboynik.productlist.model.DataState
import com.kostyarazboynik.productlist.model.ProductListItem
import com.kostyarazboynik.productlist.model.ProductListItemJson
import kotlinx.coroutines.flow.Flow

interface ProductListRemoteRepository {

    suspend fun getRemoteProductListItemsFlow(): Flow<DataState<List<ProductListItemJson>>>
}