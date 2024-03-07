package com.kostyarazboynik.productlist.repository

import com.kostyarazboynik.productlist.model.DataState
import com.kostyarazboynik.productlist.model.ProductListItem
import kotlinx.coroutines.flow.Flow

interface ProductListRemoteRepository {

    suspend fun getRemoteProductListItemsFlow(): Flow<DataState<List<ProductListItem>>>
}