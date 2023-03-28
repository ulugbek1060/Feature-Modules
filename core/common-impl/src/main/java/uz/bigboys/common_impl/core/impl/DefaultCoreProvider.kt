package uz.bigboys.common_impl.core.impl

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import uz.bigboys.common.*

class DefaultCoreProvider(
   private val appContext: Context,
   override val appRestarter: AppRestarter,
   override val commonUi: CommonUi = AndroidCommonUi(appContext),
   override val resources: Resources = AndroidResources(appContext),
   override val screenCommunication: ScreenCommunication = DefaultScreenCommunication(),
   override val globalScope: CoroutineScope = createDefaultGlobalScope(),
   override val logger: Logger = AndroidLogger(),
   override val errorHandler: ErrorHandler = DefaultErrorHandler(
      logger, commonUi, resources, appRestarter, globalScope
   )
) : CoreProvider
