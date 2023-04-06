package uz.bigboys.navigation.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.bigboys.common.Container

interface GetCurrentUsernameRepository {

   /**
    * Listen for the username of the current logged-in user.
    * @return infinite flow, always success; errors are delivered to [Container]
    */
   fun getCurrentUsername(): Flow<Container<String>>

   /**
    * Reload username flow returned by [getCurrentUsername]
    */
   fun reload()
}