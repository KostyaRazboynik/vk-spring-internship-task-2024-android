package com.kostyarazboynik.productlist.ui.product_list.list_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kostyarazboynik.productlist.databinding.ProductListItemLayoutBinding
import com.kostyarazboynik.productlist.model.ProductListItem
import com.kostyarazboynik.productlist.ui.product_list.list_adapter.diffutil.ProductListItemDiffUtilCallback

class ProductListItemAdapter(
    private val loadNewProductsCallBack: () -> Unit,
) : ListAdapter<ProductListItem, ProductListItemViewHolder>(ProductListItemDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListItemViewHolder =
        ProductListItemViewHolder(
            ProductListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ProductListItemViewHolder, position: Int) {
        holder.bind(productListItem = getItem(position))
        if (position == itemCount - 3) {
            loadNewProductsCallBack()
        }
    }

    private companion object {
        private const val TAG = "ProductListItemAdapter"
    }
}