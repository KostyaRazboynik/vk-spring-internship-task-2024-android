package com.kostyarazboynik.productlist.datasource.remote.repository

import com.kostyarazboynik.productlist.model.DataState
import com.kostyarazboynik.productlist.model.ProductListItem
import com.kostyarazboynik.productlist.model.UiState
import com.kostyarazboynik.productlist.repository.ProductListRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductListRemoteRepositoryImpl : ProductListRemoteRepository {

    override suspend fun getRemoteProductListItemsFlow(): Flow<UiState<List<ProductListItem>>> =
        flow {
            emit(UiState.Initial)

            getProductListItems().collect { state ->
                when (state) {
                    DataState.Initial -> emit(UiState.Initial)
                    is DataState.Exception -> emit(UiState.Error(state.cause.message.toString()))
                    is DataState.Result -> {
                        emit(UiState.Success(state.data))
                    }
                }
            }
        }

    private suspend fun getProductListItems(): Flow<DataState<List<ProductListItem>>> = flow {
        // TODO
    }
}