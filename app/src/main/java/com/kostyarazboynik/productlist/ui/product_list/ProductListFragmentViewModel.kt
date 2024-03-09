package com.kostyarazboynik.productlist.ui.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kostyarazboynik.productlist.extensions.launchNamed
import com.kostyarazboynik.productlist.model.ProductListItem
import com.kostyarazboynik.productlist.model.UiState
import com.kostyarazboynik.productlist.usecase.GetProductListItemsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import javax.inject.Inject

class ProductListFragmentViewModel(
    private val getProductListItemsUseCase: GetProductListItemsUseCase,
) : ViewModel() {

    private val _productListItemsFlow: MutableStateFlow<UiState<List<ProductListItem>>> =
        MutableStateFlow(UiState.Initial)
    val productListItemsFlow: StateFlow<UiState<List<ProductListItem>>> = _productListItemsFlow

    fun loadData() =
        viewModelScope.launchNamed("$TAG-viewModelScope-loadData", Dispatchers.IO) {
            _productListItemsFlow.emitAll(getProductListItemsUseCase.getProductItems())
        }

    class Factory @Inject constructor(
        private val getProductListItemsUseCase: GetProductListItemsUseCase,
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            ProductListFragmentViewModel(
                getProductListItemsUseCase
            ) as T
    }

    companion object {
        private const val TAG = "ProductListFragmentViewModel"
    }
}