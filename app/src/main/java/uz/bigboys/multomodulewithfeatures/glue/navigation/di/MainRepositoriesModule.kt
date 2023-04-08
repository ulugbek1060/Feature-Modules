package uz.bigboys.multomodulewithfeatures.glue.navigation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.bigboys.multomodulewithfeatures.glue.navigation.repositories.AdapterGetCartItemCountRepository
import uz.bigboys.multomodulewithfeatures.glue.navigation.repositories.AdapterGetCurrentUsernameRepository
import uz.bigboys.navigation.domain.repository.GetCartItemCountRepository
import uz.bigboys.navigation.domain.repository.GetCurrentUsernameRepository


@Module
@InstallIn(SingletonComponent::class)
interface MainRepositoriesModule {

   @Binds
   fun bindGetCurrentUsernameRepository(
      getCurrentUsernameRepository: AdapterGetCurrentUsernameRepository
   ): GetCurrentUsernameRepository

   @Binds
   fun bindGetCartItemCountRepository(
      getCartItemCountRepository: AdapterGetCartItemCountRepository
   ): GetCartItemCountRepository


}