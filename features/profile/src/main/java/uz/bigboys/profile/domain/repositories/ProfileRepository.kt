package uz.bigboys.profile.domain.repositories

import uz.bigboys.profile.domain.entities.Profile
import kotlinx.coroutines.flow.Flow
import uz.bigboys.common.Container

interface ProfileRepository {
    /**
     * Listen for a profile info of the current logged-in user.
     * @return infinite flow, always success; errors are delivered to [Container]
     */
    fun getProfile(): Flow<Container<Profile>>

    /**
     * Reload profile info flow returned by [getProfile]
     */
    fun reload()

    /**
     * Update username of the current logged-in user.
     */
    suspend fun editUsername(newUsername: String)
}