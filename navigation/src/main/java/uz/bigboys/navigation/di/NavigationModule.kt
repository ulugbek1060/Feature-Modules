package uz.bigboys.navigation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import uz.bigboys.common_impl.core.impl.ActivityRequired
import uz.bigboys.navigation.GlobalNavComponentRouter

@Module(includes = [NavigationBindsModule::class])
@InstallIn(SingletonComponent::class)
class NavigationModule {

   @Provides
   @IntoSet
   fun provideRouterAsActivityRequired(
      router: GlobalNavComponentRouter,
   ): ActivityRequired {
      return router
   }

}

