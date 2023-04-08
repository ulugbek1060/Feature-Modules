package uz.bigboys.multomodulewithfeatures.glue.signin.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import uz.bigboys.multomodulewithfeatures.glue.signin.AdapterSignInRouter
import uz.bigboys.sign_in.presentation.SignInRouter

@Module
@InstallIn(ActivityRetainedComponent::class)
interface SignInRouteModule {

   @Binds
   fun bindSignInRouter(router: AdapterSignInRouter): SignInRouter

}