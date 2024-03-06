package com.kostyarazboynik.productlist.ui.dagger

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.kostyarazboynik.productlist.MainActivity
import com.kostyarazboynik.productlist.ui.product_list.ProductListFragment
import com.kostyarazboynik.productlist.ui.product_list.ProductListFragmentViewModel
import dagger.BindsInstance
import dagger.Subcomponent
import kotlinx.coroutines.CoroutineScope

@UIScope
@Subcomponent(
    modules = [
    ],
)
interface UIComponent {

    val productListFragmentViewModelFactory: ProductListFragmentViewModel.Factory

    fun inject(productListFragment: ProductListFragment)

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun activity(activity: Activity): Builder

        @BindsInstance
        fun appCompatActivity(activity: AppCompatActivity): Builder

        @BindsInstance
        fun mainActivity(mainActivity: MainActivity): Builder

        @BindsInstance
        fun componentScope(
            @UICoroutineScope
            componentScope: CoroutineScope
        ): Builder

        fun build(): UIComponent
    }
}
