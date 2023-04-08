package uz.bigboys.navigation.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import uz.bigboys.common.AppRestarter
import uz.bigboys.common_impl.core.impl.ActivityRequired
import uz.bigboys.navigation.GlobalNavComponentRouter
import uz.bigboys.navigation.MainAppRestarter

@Module
@InstallIn(SingletonComponent::class)
interface NavigationBindsModule {

   @Binds
   fun provideAppRestarter(
      appRestarter: MainAppRestarter
   ): AppRestarter


}