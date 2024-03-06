package com.kostyarazboynik.productlist.ui.product_list.list_adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.kostyarazboynik.productlist.model.ProductListItem

class ProductListItemDiffUtilCallback : DiffUtil.ItemCallback<ProductListItem>() {

    override fun areItemsTheSame(oldItem: ProductListItem, newItem: ProductListItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ProductListItem, newItem: ProductListItem): Boolean =
        oldItem == newItem
}