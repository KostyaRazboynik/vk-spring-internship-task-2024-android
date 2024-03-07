package com.kostyarazboynik.productlist.datasource.remote.repository

import com.kostyarazboynik.productlist.datasource.remote.ProductListItemApi
import com.kostyarazboynik.productlist.model.DataState
import com.kostyarazboynik.productlist.model.ProductListItem
import com.kostyarazboynik.productlist.repository.ProductListRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductListRemoteRepositoryImpl @Inject constructor(
    private val service: ProductListItemApi,
) : ProductListRemoteRepository {

    override suspend fun getRemoteProductListItemsFlow(): Flow<DataState<List<ProductListItem>>> =
        getProductListItems()

    private suspend fun getProductListItems(): Flow<DataState<List<ProductListItem>>> = flow {
        try {
            val networkListResponse = service.getList()

            if (networkListResponse.isSuccessful) {
                networkListResponse.body()?.let {
                    emit(DataState.Result(it.list.map { it.networkEntityToModel() }))
                }
            } else {
                networkListResponse.errorBody()?.close()
            }
        } catch (exception : Exception) {
            emit(DataState.Exception(exception))
        }
    }
}