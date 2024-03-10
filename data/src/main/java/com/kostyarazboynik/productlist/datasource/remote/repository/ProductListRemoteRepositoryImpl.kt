package com.kostyarazboynik.productlist.datasource.remote.repository

import com.kostyarazboynik.productlist.Logger
import com.kostyarazboynik.productlist.datasource.remote.ProductListItemApi
import com.kostyarazboynik.productlist.model.DataState
import com.kostyarazboynik.productlist.model.ProductListItemJson
import com.kostyarazboynik.productlist.repository.ProductListRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductListRemoteRepositoryImpl @Inject constructor(
    private val service: ProductListItemApi,
) : ProductListRemoteRepository {

    override suspend fun getRemoteProductListItemsFlow(): Flow<DataState<List<ProductListItemJson>>> =
        getProductListItems()

    private suspend fun getProductListItems(): Flow<DataState<List<ProductListItemJson>>> = flow {
        try {
            val networkListResponse = service.getList()

            if (networkListResponse.isSuccessful) {
                networkListResponse.body()?.let { response ->
                    Logger.d(TAG, response.list.toString())
                    emit(DataState.Result(response.list))
                }
            } else {
                networkListResponse.errorBody()?.close()
            }
        } catch (exception: Exception) {
            emit(DataState.Exception(exception))
        }
    }

    private companion object {
        private const val TAG = "ProductListRemoteRepositoryImpl"
    }
}