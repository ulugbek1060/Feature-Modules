package uz.bigboys.navigation

import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import uz.bigboys.navigation.presentation.navigation.NavTab

interface DestinationsProvider {

   /**
    * Get the initial id of the Screen
    */
   @IdRes
   fun provideStartDestinationId(): Int

   /**
    * Get the Id of a main navigation graph
    */
   @NavigationRes
   fun provideNavigationGraphId(): Int

   /**
    * Get the list of tabs to be rendered at the bottom nav bar
    */
   fun provideMainTabs(): List<NavTab>

   /**
    * Get the destination id of the [TabFragment]
    */
   @IdRes
   fun provideTabsDestinationId(): Int

   /**
    * Get the destination id of fragment which shows a cart to the user
    */
   @IdRes
   fun provideCartDestinationId(): Int

}