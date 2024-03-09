package com.kostyarazboynik.productlist.dagger

import android.app.Application
import android.content.Context
import com.kostyarazboynik.productlist.MainActivity
import com.kostyarazboynik.productlist.ProductListApp
import com.kostyarazboynik.productlist.dagger.module.data.network.NetworkModule
import com.kostyarazboynik.productlist.dagger.module.data.repository.RepositoryModule
import com.kostyarazboynik.productlist.dagger.module.domain.ControllersModule
import com.kostyarazboynik.productlist.ui.dagger.UIComponent
import com.kostyarazboynik.productlist.ui.product_list.ProductListFragmentViewModel
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        NetworkModule::class,
        ControllersModule::class,
    ]
)
interface AppComponent {

    fun findViewModelFactory(): ProductListFragmentViewModel.Factory

    fun inject(app: ProductListApp)

    fun inject(activity: MainActivity)

    fun createUIComponent(): UIComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun componentScope(componentScope: CoroutineScope): Builder

        fun build(): AppComponent
    }

    companion object : ScopeComponentProvider<AppComponent>() {

        fun init(
            application: Application,
        ) = store(
            coroutineScopeName = "AppComponent"
        ) { componentScope ->
            DaggerAppComponent
                .builder()
                .componentScope(componentScope)
                .context(application)
                .application(application)
                .build()
        }
    }
}
