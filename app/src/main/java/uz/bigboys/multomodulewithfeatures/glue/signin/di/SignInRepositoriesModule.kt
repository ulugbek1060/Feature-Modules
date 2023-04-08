package uz.bigboys.multomodulewithfeatures.glue.signin.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.bigboys.multomodulewithfeatures.glue.signin.repositories.AdapterAuthServiceRepository
import uz.bigboys.multomodulewithfeatures.glue.signin.repositories.AdapterAuthTokenRepository
import uz.bigboys.multomodulewithfeatures.glue.signin.repositories.AdapterProfileRepository
import uz.bigboys.sign_in.domain.repositories.AuthServiceRepository
import uz.bigboys.sign_in.domain.repositories.AuthTokenRepository
import uz.bigboys.sign_in.domain.repositories.ProfileRepository

@Module
@InstallIn(SingletonComponent::class)
interface SignInRepositoriesModule {

   @Binds
   fun bindAuthRepository(
      authServiceRepository: AdapterAuthServiceRepository
   ): AuthServiceRepository

   @Binds
   fun bindAuthTokenRepository(
      authTokenRepository: AdapterAuthTokenRepository
   ): AuthTokenRepository

   @Binds
   fun bindProfileRepository(
      profileRepository: AdapterProfileRepository
   ): ProfileRepository
}