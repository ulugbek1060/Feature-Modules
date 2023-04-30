package uz.bigboys.multomodulewithfeatures.glue.profile.di

import uz.bigboys.profile.domain.repositories.AuthTokenRepository
import uz.bigboys.profile.domain.repositories.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.bigboys.multomodulewithfeatures.glue.profile.repositories.AdapterAuthTokenRepository
import uz.bigboys.multomodulewithfeatures.glue.profile.repositories.AdapterProfileRepository


@Module
@InstallIn(SingletonComponent::class)
interface ProfileRepositoriesModule {

    @Binds
    fun bindProfileRepository(
        profileRepository: AdapterProfileRepository
    ): ProfileRepository

    @Binds
    fun bindAuthTokenRepository(
        authTokenRepository: AdapterAuthTokenRepository
    ): AuthTokenRepository
}