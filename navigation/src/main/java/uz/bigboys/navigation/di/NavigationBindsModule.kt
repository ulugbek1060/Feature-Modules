package uz.bigboys.navigation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.bigboys.common.AppRestarter
import uz.bigboys.navigation.MainAppRestarter

@Module
@InstallIn(SingletonComponent::class)
interface NavigationBindsModule {

   @Binds
   fun provideAppRestarter(
      appRestarter: MainAppRestarter
   ): AppRestarter

}