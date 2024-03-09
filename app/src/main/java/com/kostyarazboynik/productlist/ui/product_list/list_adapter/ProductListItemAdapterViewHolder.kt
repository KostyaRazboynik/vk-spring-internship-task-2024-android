package com.kostyarazboynik.productlist.ui.product_list.list_adapter

import androidx.recyclerview.widget.RecyclerView
import com.kostyarazboynik.productlist.databinding.ProductListItemLayoutBinding
import com.kostyarazboynik.productlist.model.ProductListItem

class ProductListItemViewHolder(private val binding: ProductListItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(productListItem: ProductListItem) {

        binding.productTitle.text = productListItem.title
    }
}