package uz.bigboys.multomodulewithfeatures.glue.signup.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.bigboys.multomodulewithfeatures.glue.signup.repositories.AdapterSignUpRepository
import uz.bigboys.sign_up.domain.repositories.SignUpRepository


@Module
@InstallIn(SingletonComponent::class)
interface SignUpRepositoriesModule {

   @Binds
   fun bindSignUpRepository(signUpRepository: AdapterSignUpRepository): SignUpRepository
}