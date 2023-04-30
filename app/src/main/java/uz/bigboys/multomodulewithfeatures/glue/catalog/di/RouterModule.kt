package uz.bigboys.multomodulewithfeatures.glue.catalog.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import uz.bigboys.catalog.presentation.CatalogFilterRouter
import uz.bigboys.catalog.presentation.CatalogRouter
import uz.bigboys.multomodulewithfeatures.glue.catalog.AdapterCatalogFilterRouter
import uz.bigboys.multomodulewithfeatures.glue.catalog.AdapterCatalogRouter

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RouterModule {

    @Binds
    fun bindCatalogRouter(
        catalogRouter: AdapterCatalogRouter
    ): CatalogRouter

    @Binds
    fun bindCatalogFilterRouter(
        router: AdapterCatalogFilterRouter
    ): CatalogFilterRouter

}