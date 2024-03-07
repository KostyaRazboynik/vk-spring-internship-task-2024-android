package com.kostyarazboynik.productlist.core.dagger.module.domain

import com.kostyarazboynik.productlist.repository.ProductListRemoteRepository
import com.kostyarazboynik.productlist.usecase.GetProductListItemsUseCase
import dagger.Module
import dagger.Provides

@Module
object ControllersModule {

    @Provides
    fun provideTGetProductListItemsUseCase(
        productListRemoteRepository: ProductListRemoteRepository,
    ): GetProductListItemsUseCase {
        return GetProductListItemsUseCase(
            productListRemoteRepository,
        )
    }
}
