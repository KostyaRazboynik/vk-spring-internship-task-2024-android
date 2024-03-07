package com.kostyarazboynik.productlist.ui.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kostyarazboynik.productlist.repository.ProductListRemoteRepository
import com.kostyarazboynik.productlist.ui.dagger.UIScope
import com.kostyarazboynik.productlist.usecase.GetProductListItemsUseCase
import javax.inject.Inject

class ProductListFragmentViewModel(
    private val getProductListItemsUseCase: GetProductListItemsUseCase,
) : ViewModel() {

    @UIScope
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