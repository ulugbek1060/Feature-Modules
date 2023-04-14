package com.example.profile.domain

import com.example.profile.domain.repositories.AuthTokenRepository
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