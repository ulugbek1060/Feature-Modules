package uz.bigboys.data.products.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.bigboys.data.products.sources.DiscountsDataSource
import uz.bigboys.data.products.sources.InMemoryDiscountsDataSource
import uz.bigboys.data.products.sources.InMemoryProductsDataSource
import uz.bigboys.data.products.sources.ProductsDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ProductSourcesModule {

    @Binds
    @Singleton
    fun bindProductsSource(
        productsDataSource: InMemoryProductsDataSource
    ): ProductsDataSource

    @Binds
    @Singleton
    fun bindDiscountsSource(
        discountsDataSource: InMemoryDiscountsDataSource
    ): DiscountsDataSource

}