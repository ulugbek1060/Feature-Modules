package uz.bigboys.multomodulewithfeatures.glue.signin.repositories

import uz.bigboys.data.AccountsDataRepository
import uz.bigboys.sign_in.domain.repositories.AuthServiceRepository
import javax.inject.Inject

class AdapterAuthServiceRepository
@Inject constructor(
   private val accountsDataRepository: AccountsDataRepository
) : AuthServiceRepository {

   override suspend fun signIn(email: String, password: String): String {
      return accountsDataRepository.signIn(email, password)
   }

}