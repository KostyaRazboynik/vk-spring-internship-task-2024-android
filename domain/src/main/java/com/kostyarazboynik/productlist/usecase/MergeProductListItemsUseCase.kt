package com.kostyarazboynik.productlist.usecase

import com.kostyarazboynik.productlist.model.ProductListItem
import com.kostyarazboynik.productlist.model.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MergeProductListItemsUseCase {

    fun mergeUiStateFlow(
        oldUiState: UiState<List<ProductListItem>>,
        newUiState: UiState<List<ProductListItem>>,
    ): Flow<UiState<List<ProductListItem>>> = flow {
        if (newUiState !is UiState.Success || oldUiState !is UiState.Success) {
            emit(newUiState)
        } else {
            emit(UiState.Success(oldUiState.data + newUiState.data))
        }
    }
}