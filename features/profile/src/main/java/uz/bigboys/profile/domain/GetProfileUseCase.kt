package uz.bigboys.profile.domain

import uz.bigboys.profile.domain.entities.Profile
import uz.bigboys.profile.domain.repositories.ProfileRepository
import kotlinx.coroutines.flow.Flow
import uz.bigboys.common.Container
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {

    /**
     * Listen for a profile info of the current logged-in user.
     * @return infinite flow, always success; errors are delivered to [Container]
     */
    fun getProfile(): Flow<Container<Profile>> {
        return profileRepository.getProfile()
    }

    /**
     * Try to reload profile flow returned by [getProfile].
     */
    fun reload() {
        profileRepository.reload()
    }
}