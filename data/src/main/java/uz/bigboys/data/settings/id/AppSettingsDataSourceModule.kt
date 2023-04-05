package uz.bigboys.data.settings.id

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.bigboys.data.settings.SettingsDataSource
import uz.bigboys.data.settings.SharedPreferenceSettingsDataSource

@Module
@InstallIn(SingletonComponent::class)
interface AppSettingsDataSourceModule {

   @Binds
   fun bindSettingsSource(
      settingsDataSource: SharedPreferenceSettingsDataSource
   ): SettingsDataSource

}