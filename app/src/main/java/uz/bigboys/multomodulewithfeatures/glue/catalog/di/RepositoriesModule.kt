package uz.bigboys.multomodulewithfeatures.glue.catalog.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.bigboys.catalog.domain.repositories.CartRepository
import uz.bigboys.catalog.domain.repositories.ProductsRepository
import uz.bigboys.multomodulewithfeatures.glue.catalog.repositories.AdapterCartRepository
import uz.bigboys.multomodulewithfeatures.glue.catalog.repositories.AdapterProductsRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun provideProductsRepository(
        repository: AdapterProductsRepository
    ): ProductsRepository

    @Binds
    fun provideCartRepository(
        repository: AdapterCartRepository
    ): CartRepository

}