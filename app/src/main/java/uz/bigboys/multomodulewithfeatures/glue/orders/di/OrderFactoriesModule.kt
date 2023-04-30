package uz.bigboys.multomodulewithfeatures.glue.orders.di

import uz.bigboys.orders.domain.factories.PriceFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.bigboys.multomodulewithfeatures.glue.orders.factories.DefaultOrderPriceFactory

@Module
@InstallIn(SingletonComponent::class)
interface OrderFactoriesModule {

    @Binds
    fun bindOrderPriceFactory(
        priceFactory: DefaultOrderPriceFactory
    ): PriceFactory

}