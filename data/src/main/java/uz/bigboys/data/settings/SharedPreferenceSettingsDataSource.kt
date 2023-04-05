package uz.bigboys.data.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class SharedPreferenceSettingsDataSource @Inject constructor(
   @ApplicationContext context: Context
) : SettingsDataSource, SharedPreferences.OnSharedPreferenceChangeListener {

   private val preferences = context.getSharedPreferences(
      PREFERENCES_NAME, Context.MODE_PRIVATE
   )
   private val tokenFlow = MutableStateFlow<String?>(null)

   override fun setToken(token: String?) {
      preferences.edit {
         if (token != null) {
            putString(PREF_TOKEN, token)
         } else {
            remove(PREF_TOKEN)
         }
      }
   }

   override fun getToken(): String? {
      return preferences.getString(PREF_TOKEN, null)
   }

   override fun listenToken(): Flow<String?> {
      return tokenFlow
   }

   override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
      tokenFlow.value = getToken()
   }

   private companion object {
      const val PREFERENCES_NAME = "preferences"
      const val PREF_TOKEN = "token"
   }

}