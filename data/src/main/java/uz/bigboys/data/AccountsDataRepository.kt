package uz.bigboys.data

import kotlinx.coroutines.flow.Flow
import uz.bigboys.common.Container
import uz.bigboys.data.account.entity.AccountDataEntity
import uz.bigboys.data.account.entity.SignUpDataEntity

interface AccountsDataRepository {

   /**
    * Listen for the current account
    * Returns: infinite flow, always success; errors delivered to Container
    */
   fun getAccount(): Flow<Container<AccountDataEntity>>

   /**
    * Update the username of the current logged-in user
    */
   suspend fun setAccountUsername(username: String)

   /**
    * Exchange email & password to auth the token
    */
   suspend fun signIn(email: String, password: String): String

   /**
    * Create a new account
    * @throws AccountAlreadyExistsDataException
    */
   suspend fun signUp(signUpData: SignUpDataEntity)

   /**
    * Reload the flow returned by [getAccount]
    */
   fun reload()

}