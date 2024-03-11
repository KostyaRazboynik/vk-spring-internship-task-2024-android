package com.kostyarazboynik.productlist.usecase

import com.kostyarazboynik.productlist.model.DataState
import com.kostyarazboynik.productlist.model.ProductListItem
import com.kostyarazboynik.productlist.model.UiState
import com.kostyarazboynik.productlist.repository.ProductListRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetProductListItemsUseCase(
    private val productListRemoteRepository: ProductListRemoteRepository
) {

    suspend fun getProductItemsFlow(limit: Int, skip: Int): Flow<UiState<List<ProductListItem>>> = flow {
        productListRemoteRepository.getRemoteProductListItemsFlow(limit, skip).collect { state ->
            when (state) {
                DataState.Initial -> emit(UiState.Initial)
                is DataState.Exception -> emit(UiState.Error(state.cause.message.toString()))
                is DataState.Result -> {
                    emit(UiState.Success(state.data.map { it.toProductListItem() }))
                }
            }
        }
    }
}
