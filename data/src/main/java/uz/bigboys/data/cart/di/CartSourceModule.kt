package uz.bigboys.data.cart.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.bigboys.data.cart.source.CartDataSource
import uz.bigboys.data.cart.source.InMemoryCartDataSource

@Module
@InstallIn(SingletonComponent::class)
interface CartSourceModule {

   @Binds
   fun bindCartSource(
      inMemoryCartDataSource: InMemoryCartDataSource
   ): CartDataSource
}