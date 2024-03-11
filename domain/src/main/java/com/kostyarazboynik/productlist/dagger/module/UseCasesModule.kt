package com.kostyarazboynik.productlist.dagger.module

import com.kostyarazboynik.productlist.repository.ProductListRemoteRepository
import com.kostyarazboynik.productlist.usecase.GetProductListItemsUseCase
import com.kostyarazboynik.productlist.usecase.MergeProductListItemsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object UseCasesModule {

    @Provides
    @Singleton
    fun provideGetProductListItemsUseCase(
        productListRemoteRepository: ProductListRemoteRepository,
    ): GetProductListItemsUseCase {
        return GetProductListItemsUseCase(
            productListRemoteRepository,
        )
    }

    @Provides
    @Singleton
    fun provideMergeProductListItemsUIStateUseCase(): MergeProductListItemsUseCase {
        return MergeProductListItemsUseCase()
    }
}
