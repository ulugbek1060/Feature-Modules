package uz.bigboys.common_impl.core.impl

import android.content.Context
import uz.bigboys.common.Resources

/**
 * Default implementation of [Resources] which fetches strings from an application context.
 */
class AndroidResources(
   private val applicationContext: Context,
) : Resources {

   override fun getString(id: Int): String {
      return applicationContext.getString(id)
   }

   override fun getString(id: Int, vararg placeholders: Any): String {
      return applicationContext.getString(id, placeholders)
   }

}