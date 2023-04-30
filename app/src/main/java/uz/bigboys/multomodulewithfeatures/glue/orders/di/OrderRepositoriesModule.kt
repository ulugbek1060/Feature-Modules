package uz.bigboys.multomodulewithfeatures.glue.orders.di

import uz.bigboys.orders.domain.repositories.CartRepository
import uz.bigboys.orders.domain.repositories.OrdersRepository
import uz.bigboys.orders.domain.repositories.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.bigboys.multomodulewithfeatures.glue.orders.repositories.AdapterCartRepository
import uz.bigboys.multomodulewithfeatures.glue.orders.repositories.AdapterOrdersRepository
import uz.bigboys.multomodulewithfeatures.glue.orders.repositories.AdapterProductsRepository

@Module
@InstallIn(SingletonComponent::class)
interface OrderRepositoriesModule {

    @Binds
    fun bindOrdersRepository(
        ordersRepository: AdapterOrdersRepository
    ): OrdersRepository

    @Binds
    fun bindCartRepository(
        cartRepository: AdapterCartRepository
    ): CartRepository

    @Binds
    fun bindProductsRepository(
        productsRepository: AdapterProductsRepository
    ): ProductsRepository

}