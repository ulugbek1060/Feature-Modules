package uz.bigboys.data.account.source

import uz.bigboys.data.account.entity.AccountDataEntity
import uz.bigboys.data.account.entity.SignUpDataEntity

interface AccountsDataSource {

   suspend fun signIn(email: String, password: String): String

   suspend fun signUp(signUpData: SignUpDataEntity)

   suspend fun getAccount(): AccountDataEntity

   suspend fun setAccountUsername(username: String): AccountDataEntity
}