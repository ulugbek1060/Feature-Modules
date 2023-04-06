package uz.bigboys.navigation.domain

import kotlinx.coroutines.flow.Flow
import uz.bigboys.common.Container
import uz.bigboys.navigation.domain.repository.GetCurrentUsernameRepository
import javax.inject.Inject

class GetCurrentUsernameUseCase @Inject constructor(
   private val getCurrentUsernameRepository: GetCurrentUsernameRepository
) {

   /**
    * Listen for the username of the current logged-in user.
    * @return infinite flow, always success; errors are delivered to [Container]
    */
   operator fun invoke(): Flow<Container<String>> {
      return getCurrentUsernameRepository.getCurrentUsername()
   }

}