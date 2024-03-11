package com.kostyarazboynik.productlist.datasource.dagger.module.repository

import com.kostyarazboynik.productlist.datasource.remote.repository.ProductListRemoteRepositoryImpl
import com.kostyarazboynik.productlist.repository.ProductListRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface RepositoryModule {

    @Reusable
    @Binds
    fun bindRemoteProductListItemRepository(
        productListRemoteRepository: ProductListRemoteRepositoryImpl,
    ): ProductListRemoteRepository
}
