package uz.bigboys.multomodulewithfeatures.glue.signup.repositories

import uz.bigboys.data.AccountsDataRepository
import uz.bigboys.data.account.entity.SignUpDataEntity
import uz.bigboys.sign_up.domain.entity.SignUpData
import uz.bigboys.sign_up.domain.exceptions.AccountAlreadyExistsException
import uz.bigboys.sign_up.domain.repositories.SignUpRepository
import javax.inject.Inject

class AdapterSignUpRepository @Inject constructor(
   private val accountsRepository: AccountsDataRepository
) : SignUpRepository {

   override suspend fun signUp(signUpData: SignUpData) = try {
      accountsRepository.signUp(
         SignUpDataEntity(
            email = signUpData.email,
            username = signUpData.username,
            password = signUpData.password,
         )
      )
   } catch (e: Exception) {
      throw AccountAlreadyExistsException()
   }
}