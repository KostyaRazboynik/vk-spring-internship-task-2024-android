package com.kostyarazboynik.productlist

import com.kostyarazboynik.productlist.core.dagger.ScopeComponentProvider
import com.kostyarazboynik.productlist.ui.dagger.UIComponent
import kotlinx.coroutines.CoroutineScope


class MainActivityUIComponentProviderDelegate : ScopeComponentProvider<UIComponent>() {

    fun onActivityCreate(
        createComponent: (CoroutineScope) -> UIComponent
    ) = store(
        coroutineScopeName = "MainActivity.UIComponent",
        createComponent = createComponent
    )

    fun onActivityDestroy() = clear()
}
