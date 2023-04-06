package uz.bigboys.navigation.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes

class NavTab(

   /**
    * Destination id which is launched when a user taps on this tab
    */
   @IdRes val destinationId: Int,


   /**
    * THe name displayed under the icon
    */
   val title: String,

   /**
    * Tabs icon.
    */
   @DrawableRes val icon: Int

) : java.io.Serializable