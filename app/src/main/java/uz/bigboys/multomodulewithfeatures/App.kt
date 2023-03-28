package uz.bigboys.multomodulewithfeatures

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import uz.bigboys.common.Core
import uz.bigboys.common.CoreProvider
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

   @Inject
   lateinit var coreProvider: CoreProvider

   override fun onCreate() {
      super.onCreate()
      Core.init(coreProvider)
   }
}