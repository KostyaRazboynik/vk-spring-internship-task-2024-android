package com.kostyarazboynik.productlist.ui.product_list.list_adapter

import androidx.recyclerview.widget.RecyclerView
import com.kostyarazboynik.productlist.databinding.ProductListItemLayoutBinding
import com.kostyarazboynik.productlist.model.ProductListItem

class ProductListItemViewHolder(private val binding: ProductListItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(productListItem: ProductListItem) {
        binding.apply {
            productTitle.text = productListItem.title
            productDescription.text = productListItem.description
            productImage.setImageURI(productListItem.thumbnail)
        }
    }
}