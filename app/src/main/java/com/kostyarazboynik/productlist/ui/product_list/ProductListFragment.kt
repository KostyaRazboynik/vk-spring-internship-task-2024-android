package com.kostyarazboynik.productlist.ui.product_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kostyarazboynik.productlist.databinding.FragmentProductListLayoutBinding
import javax.inject.Inject

class ProductListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ProductListFragmentViewModel.Factory

    private val viewModel by viewModels<ProductListFragmentViewModel> {
        viewModelFactory
    }

    private val binding: FragmentProductListLayoutBinding by viewBinding(createMethod = CreateMethod.INFLATE)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        private const val TAG = "ProductListFragment"
        fun newInstance() = ProductListFragment()
    }
}