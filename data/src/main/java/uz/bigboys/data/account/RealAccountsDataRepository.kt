package uz.bigboys.data.account

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import uz.bigboys.common.AuthException
import uz.bigboys.common.Container
import uz.bigboys.common.flow.LazyFlowSubjectFactory
import uz.bigboys.data.AccountsDataRepository
import uz.bigboys.data.account.entity.AccountDataEntity
import uz.bigboys.data.account.entity.SignUpDataEntity
import uz.bigboys.data.account.source.AccountsDataSource
import uz.bigboys.data.settings.SettingsDataSource
import javax.inject.Inject

class RealAccountsDataRepository @Inject constructor(
   private val accountsDataSource: AccountsDataSource,
   private val settingsDataSource: SettingsDataSource,
   scope: CoroutineScope,
   lazyFlowSubjectFactory: LazyFlowSubjectFactory,
) : AccountsDataRepository {

   private val accountLazyFlowSubject = lazyFlowSubjectFactory.create {
      accountsDataSource.getAccount()
   }

   init {
      scope.launch {
         settingsDataSource.listenToken().collect {
            if (it == null) {
               accountLazyFlowSubject.updateWith(Container.Error(AuthException()))
            } else {
               accountLazyFlowSubject.newAsyncLoad(silently = true)
            }
         }
      }
   }

   override fun getAccount(): Flow<Container<AccountDataEntity>> {
      return accountLazyFlowSubject.listen()
   }

   override suspend fun setAccountUsername(username: String) {
      val newAccount = accountsDataSource.setAccountUsername(username)
      accountLazyFlowSubject.updateWith(Container.Success(newAccount))
   }

   override suspend fun signIn(email: String, password: String): String {
      return accountsDataSource.signIn(email, password)
   }

   override suspend fun signUp(signUpData: SignUpDataEntity) {
      accountsDataSource.signUp(signUpData)
   }

   override fun reload() {
      accountLazyFlowSubject.newAsyncLoad()
   }

}