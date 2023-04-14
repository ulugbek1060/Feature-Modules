package uz.bigboys.multomodulewithfeatures.glue.signup.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.bigboys.multomodulewithfeatures.glue.signup.AdapterSignUpRouter
import uz.bigboys.sign_up.presentation.SignUpRouter

@Module
@InstallIn(SingletonComponent::class)
interface SignUpRouterModel {

   @Binds
   fun bindSignUpRouter(signUpRouter: AdapterSignUpRouter): SignUpRouter
}