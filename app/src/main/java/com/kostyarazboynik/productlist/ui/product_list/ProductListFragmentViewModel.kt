package com.kostyarazboynik.productlist.ui.product_list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kostyarazboynik.productlist.ui.dagger.UIScope
import javax.inject.Inject

class ProductListFragmentViewModel(
    private val context: Context,
) : ViewModel() {

    @UIScope
    class Factory @Inject constructor(
        private val context: Context,
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            ProductListFragmentViewModel(
                context
            ) as T
    }

    companion object {
        private const val TAG = "ProductListFragmentViewModel"
    }
}