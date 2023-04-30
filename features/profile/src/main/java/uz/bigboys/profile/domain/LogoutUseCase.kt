package uz.bigboys.profile.domain

import uz.bigboys.profile.domain.repositories.AuthTokenRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authTokenRepository: AuthTokenRepository
) {

    /**
     * Logout from the app.
     */
    suspend fun logout() {
        authTokenRepository.clearToken()
    }

}