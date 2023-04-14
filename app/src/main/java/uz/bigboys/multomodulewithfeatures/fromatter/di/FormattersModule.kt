package uz.bigboys.multomodulewithfeatures.fromatter.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.bigboys.multomodulewithfeatures.fromatter.DateTimeFormatter
import uz.bigboys.multomodulewithfeatures.fromatter.DefaultDateTimeFormatter
import uz.bigboys.multomodulewithfeatures.fromatter.DefaultPriceFormatter
import uz.bigboys.multomodulewithfeatures.fromatter.PriceFormatter

@Module
@InstallIn(SingletonComponent::class)
interface FormattersModule {

    @Binds
    fun bindDateTimeFormatter(
        formatter: DefaultDateTimeFormatter
    ): DateTimeFormatter

    @Binds
    fun bindPriceFormatter(
        formatter: DefaultPriceFormatter
    ): PriceFormatter
}