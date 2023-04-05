package uz.bigboys.data.account.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.bigboys.data.AccountsDataRepository
import uz.bigboys.data.account.RealAccountsDataRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface AccountRepositoryModule {
   @Binds
   @Singleton
   fun bindAccountsRepository(
      accountsDataRepository: RealAccountsDataRepository
   ): AccountsDataRepository

}