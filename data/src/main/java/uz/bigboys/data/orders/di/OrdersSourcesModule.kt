package uz.bigboys.data.orders.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.bigboys.data.orders.source.InMemoryOrdersDataSource
import uz.bigboys.data.orders.source.OrdersDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface OrdersSourcesModule {

   @Binds
   @Singleton
   fun bindOrdersDataSource(
      ordersDataSource: InMemoryOrdersDataSource,
   ): OrdersDataSource

}