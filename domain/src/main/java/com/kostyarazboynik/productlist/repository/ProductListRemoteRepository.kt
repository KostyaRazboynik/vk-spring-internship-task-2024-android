package com.kostyarazboynik.productlist.repository

import com.kostyarazboynik.productlist.model.DataState
import com.kostyarazboynik.productlist.model.ProductListItemJson
import kotlinx.coroutines.flow.Flow

interface ProductListRemoteRepository {
    suspend fun getRemoteProductListItemsFlow(
        limit: Int,
        skip: Int,
    ): Flow<DataState<List<ProductListItemJson>>>
}