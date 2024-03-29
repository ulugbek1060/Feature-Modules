package uz.bigboys.profile.domain.repositories

interface AuthTokenRepository {

    /**
     * Clear auth token saved in the app.
     */
    suspend fun clearToken()
}