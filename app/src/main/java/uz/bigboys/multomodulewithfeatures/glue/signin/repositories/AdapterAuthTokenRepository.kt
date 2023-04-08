package uz.bigboys.multomodulewithfeatures.glue.signin.repositories

import uz.bigboys.data.settings.SettingsDataSource
import uz.bigboys.sign_in.domain.repositories.AuthTokenRepository
import javax.inject.Inject

class AdapterAuthTokenRepository
@Inject constructor(
   private val settingsDataSource: SettingsDataSource,
) : AuthTokenRepository {
   override suspend fun setToken(token: String?) {
      settingsDataSource.setToken(token)
   }

   override suspend fun getToken(): String? {
      return settingsDataSource.getToken()
   }
}