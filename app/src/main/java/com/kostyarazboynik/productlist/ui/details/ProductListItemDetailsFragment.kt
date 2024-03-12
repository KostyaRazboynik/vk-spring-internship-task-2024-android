package com.kostyarazboynik.productlist.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kostyarazboynik.productlist.R
import com.kostyarazboynik.productlist.databinding.FragmentProductListItemDetailsLayoutBinding
import com.kostyarazboynik.productlist.model.ProductListItem

class ProductListItemDetailsFragment : Fragment() {

    private val binding: FragmentProductListItemDetailsLayoutBinding by viewBinding(createMethod = CreateMethod.INFLATE)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    @Suppress("DEPRECATION")
    private fun setUpViews() {
        binding.apply {
            (arguments?.getParcelable(PRODUCT_LIST_ITEM_KEY) as? ProductListItem)?.let { productListItem ->
                val context = requireContext()
                productImage.setImageURI(productListItem.thumbnail)
                productTitle.text = productListItem.title
                productDescription.text = productListItem.description
                productPrice.text = context.getString(R.string.product_price, productListItem.price)
                productDiscountPercentage.text =
                    context.getString(
                        R.string.product_discount,
                        productListItem.discountPercentage.toString()
                    )
                productRating.text =
                    context.getString(R.string.product_rating, productListItem.rating.toString())
                productStock.text = context.getString(R.string.product_stock, productListItem.stock)
                productBrand.text = context.getString(R.string.product_brand, productListItem.brand)
                productCategory.text =
                    context.getString(R.string.product_category, productListItem.category)
            }
        }
    }

    companion object {
        private const val TAG = "ProductListItemDetailsFragment"
        private const val PRODUCT_LIST_ITEM_KEY = "product_list_item"
        fun newInstance(
            productListItem: ProductListItem,
        ) = ProductListItemDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(PRODUCT_LIST_ITEM_KEY, productListItem)
            }
        }
    }
}
