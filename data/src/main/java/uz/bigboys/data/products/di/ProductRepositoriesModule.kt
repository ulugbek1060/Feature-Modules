package uz.bigboys.data.products.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.bigboys.data.ProductsDataRepository
import uz.bigboys.data.products.RealProductsDataRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ProductRepositoriesModule {

    @Singleton
    @Binds
    fun bindProductRepository(
        productsDataRepository: RealProductsDataRepository
    ): ProductsDataRepository
}