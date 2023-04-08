package uz.bigboys.multomodulewithfeatures.glue.navigation.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.bigboys.common.Container
import uz.bigboys.data.AccountsDataRepository
import uz.bigboys.navigation.domain.repository.GetCurrentUsernameRepository
import javax.inject.Inject

class AdapterGetCurrentUsernameRepository
@Inject constructor(
   private val accountsRepository: AccountsDataRepository
) : GetCurrentUsernameRepository{

   override fun getCurrentUsername(): Flow<Container<String>> {
      return accountsRepository.getAccount().map { container ->
         container.map { it.username }
      }
   }

   override fun reload() {
      accountsRepository.reload()
   }
}