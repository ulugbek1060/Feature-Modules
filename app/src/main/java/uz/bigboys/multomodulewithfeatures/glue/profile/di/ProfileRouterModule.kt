package uz.bigboys.multomodulewithfeatures.glue.profile.di

import uz.bigboys.profile.presentation.ProfileRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import uz.bigboys.multomodulewithfeatures.glue.profile.AdapterProfileRouter

@Module
@InstallIn(ActivityRetainedComponent::class)
interface ProfileRouterModule {

    @Binds
    fun bindProfileRouter(
        profileRouter: AdapterProfileRouter,
    ): ProfileRouter

}