package uz.bigboys.data.cart.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.bigboys.data.CartDataRepository
import uz.bigboys.data.cart.RealCartDataRepository


@Module
@InstallIn(SingletonComponent::class)
interface CartRepositoriesModule {

   @Binds
   fun bindCartRepository(
      realCartDataRepository: RealCartDataRepository
   ): CartDataRepository
}