package uz.bigboys.data.account.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.bigboys.data.account.source.AccountsDataSource
import uz.bigboys.data.account.source.InMemoryAccountsDataSource
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface AccountSourceModule {

   @Binds
   @Singleton
   fun bindAccountSource(
      accountsDataSource: InMemoryAccountsDataSource
   ): AccountsDataSource

}