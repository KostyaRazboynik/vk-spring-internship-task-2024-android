package com.kostyarazboynik.productlist.ui.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kostyarazboynik.productlist.extensions.launchNamed
import com.kostyarazboynik.productlist.model.ProductListItem
import com.kostyarazboynik.productlist.model.UiState
import com.kostyarazboynik.productlist.ui.dagger.UIScope
import com.kostyarazboynik.productlist.usecase.GetProductListItemsUseCase
import com.kostyarazboynik.productlist.usecase.MergeProductListItemsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class ProductListFragmentViewModel(
    private val getProductListItemsUseCase: GetProductListItemsUseCase,
    private val mergeProductListItemsUseCase: MergeProductListItemsUseCase,
) : ViewModel() {

    private val _productListItemsFlow: MutableStateFlow<UiState<List<ProductListItem>>> =
        MutableStateFlow(UiState.Initial)
    val productListItemsFlow: StateFlow<UiState<List<ProductListItem>>> = _productListItemsFlow

    fun loadData() {
        viewModelScope.launchNamed("$TAG-viewModelScope-loadData", Dispatchers.IO) {

            val newUiStateFlow = getProductListItemsUseCase.getProductItemsFlow(
                limit = RESPONSE_LIMIT,
                skip = countResponseSkip()
            ).stateIn(viewModelScope)

            _productListItemsFlow.emitAll(
                mergeProductListItemsUseCase.mergeUiStateFlow(
                    oldUiState = _productListItemsFlow.value,
                    newUiState = newUiStateFlow.value,
                )
            )
        }
    }

    private fun countResponseSkip(): Int {
        val value = _productListItemsFlow.value
        return if (value is UiState.Success) {
            value.data.size
        } else {
            0
        }
    }

    class Factory @Inject constructor(
        private val getProductListItemsUseCase: GetProductListItemsUseCase,
        private val mergeProductListItemsUseCase: MergeProductListItemsUseCase
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            ProductListFragmentViewModel(
                getProductListItemsUseCase,
                mergeProductListItemsUseCase,
            ) as T
    }

    companion object {
        private const val TAG = "ProductListFragmentViewModel"
        private const val RESPONSE_LIMIT = 20
    }
}
