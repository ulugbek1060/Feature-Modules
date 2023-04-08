package uz.bigboys.multomodulewithfeatures.glue.navigation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import uz.bigboys.multomodulewithfeatures.glue.navigation.DefaultMainRouter
import uz.bigboys.navigation.presentation.MainRouter

@Module
@InstallIn(ActivityRetainedComponent::class)
interface MainRouterModule {

   @Binds
   fun bindMainRouter(router: DefaultMainRouter): MainRouter

}