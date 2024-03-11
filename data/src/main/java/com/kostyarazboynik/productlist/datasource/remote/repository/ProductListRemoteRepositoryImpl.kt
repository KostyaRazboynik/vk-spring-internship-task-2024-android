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

    override suspend fun getRemoteProductListItemsFlow(
        limit: Int,
        skip: Int
    ): Flow<DataState<List<ProductListItemJson>>> =
        getProductListItems(limit, skip)

    private suspend fun getProductListItems(
        limit: Int,
        skip: Int
    ): Flow<DataState<List<ProductListItemJson>>> = flow {
        try {
            val networkListResponse = service.getList(limit, skip)

            if (networkListResponse.isSuccessful) {
                networkListResponse.body()?.let { response ->
                    Logger.d(
                        TAG,
                        "networkListResponse.isSuccessful, list.size=${response.list.size}"
                    )
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