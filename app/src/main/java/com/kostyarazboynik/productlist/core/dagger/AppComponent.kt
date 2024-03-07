package com.kostyarazboynik.productlist.core.dagger

import android.app.Application
import android.content.Context
import com.kostyarazboynik.productlist.MainActivity
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.CoroutineScope
import com.kostyarazboynik.productlist.ProductListApp
import com.kostyarazboynik.productlist.core.dagger.module.data.RepositoryModule
import com.kostyarazboynik.productlist.core.dagger.module.domain.ControllersModule
import com.kostyarazboynik.productlist.dagger.DaggerAppComponent
import com.kostyarazboynik.productlist.ui.dagger.UIComponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        ControllersModule::class,
    ]
)
interface AppComponent {

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
            DaggerAppComponent.builder()
                .componentScope(componentScope)
                .context(application)
                .application(application)
                .build()
        }
    }
}
