package uz.bigboys.multomodulewithfeatures.glue.signin.repositories

import uz.bigboys.common.AuthException
import uz.bigboys.common.unwrapFirst
import uz.bigboys.data.AccountsDataRepository
import uz.bigboys.sign_in.domain.repositories.ProfileRepository
import javax.inject.Inject

class AdapterProfileRepository
@Inject constructor(
   private val accountsDataRepository: AccountsDataRepository,
) : ProfileRepository {
   override suspend fun canLoadProfile(): Boolean {
      return try {
         accountsDataRepository.getAccount().unwrapFirst()
         true
      } catch (ignored: AuthException) {
         false
      }
   }
}