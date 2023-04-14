package uz.bigboys.multomodulewithfeatures.glue.profile.repositories

import com.example.profile.domain.repositories.AuthTokenRepository
import uz.bigboys.data.settings.SettingsDataSource
import javax.inject.Inject

class AdapterAuthTokenRepository @Inject constructor(
    private val settingsDataSource: SettingsDataSource,
) : AuthTokenRepository {

    override suspend fun clearToken() {
        settingsDataSource.setToken(null)
    }

}