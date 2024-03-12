package com.kostyarazboynik.productlist.ui.product_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kostyarazboynik.productlist.R
import com.kostyarazboynik.productlist.dagger.AppComponent
import com.kostyarazboynik.productlist.databinding.FragmentProductListLayoutBinding
import com.kostyarazboynik.productlist.extensions.launchNamed
import com.kostyarazboynik.productlist.model.ProductListItem
import com.kostyarazboynik.productlist.model.UiState
import com.kostyarazboynik.productlist.ui.details.ProductListItemDetailsFragment
import com.kostyarazboynik.productlist.ui.product_list.list_adapter.ProductListItemAdapter

class ProductListFragment : Fragment() {

    private val binding: FragmentProductListLayoutBinding by viewBinding(createMethod = CreateMethod.INFLATE)

    private val viewModel by viewModels<ProductListFragmentViewModel> {
        AppComponent.component.findViewModelFactory()
    }

    private val listAdapter: ProductListItemAdapter
        get() = binding.recyclerView.adapter as ProductListItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        loadData()
        updateUI()
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.apply {
            adapter = ProductListItemAdapter(
                loadNewProductsCallBack = { loadData() },
                onItemClickListener = { productListItem ->
                    navigateToProductItemDetailsFragment(productListItem)
                }
            )
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun navigateToProductItemDetailsFragment(productListItem: ProductListItem) {
        activity?.supportFragmentManager?.let { manager ->
            val transaction = manager.beginTransaction()
            val fragment = ProductListItemDetailsFragment.newInstance(productListItem)
            transaction.replace(R.id.frame_content, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun loadData() = viewModel.loadData()

    private fun updateUI() =
        viewModel.viewModelScope.launchNamed("$TAG-viewModelScope-updateUI") {
            updateStateUI()
        }

    private suspend fun updateStateUI() {
        viewModel.productListItemsFlow.collect { uiState ->
            when (uiState) {
                is UiState.Success -> listAdapter.submitList(uiState.data)
                is UiState.Initial,
                is UiState.Error -> Unit
            }
        }
    }

    companion object {
        private const val TAG = "ProductListFragment"
        fun newInstance() = ProductListFragment()
    }
}
