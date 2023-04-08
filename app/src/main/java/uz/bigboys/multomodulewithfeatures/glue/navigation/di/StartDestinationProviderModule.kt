package uz.bigboys.multomodulewithfeatures.glue.navigation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.bigboys.multomodulewithfeatures.glue.navigation.DefaultDestinationsProvider
import uz.bigboys.navigation.DestinationsProvider


@Module
@InstallIn(SingletonComponent::class)
interface StartDestinationProviderModule {

   @Binds
   fun bindStartDestinationProvider(
      startDestinationProvider: DefaultDestinationsProvider
   ): DestinationsProvider

}