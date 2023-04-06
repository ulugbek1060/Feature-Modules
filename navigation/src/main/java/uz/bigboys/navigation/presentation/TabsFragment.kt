package uz.bigboys.navigation.presentation

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import uz.bigboys.navigation.DestinationsProvider
import uz.bigboys.navigation.R
import uz.bigboys.navigation.databinding.FragmentTabsBinding
import uz.bigboys.navigation.presentation.navigation.NavigationMode
import uz.bigboys.navigation.presentation.navigation.NavigationModeHolder
import uz.bigboys.presentation.viewBinding
import javax.inject.Inject

class TabsFragment : Fragment(R.layout.fragment_tabs) {

   @Inject
   lateinit var destinationsProvider: DestinationsProvider

   @Inject
   lateinit var navigationModeHolder: NavigationModeHolder

   private val binding by viewBinding<FragmentTabsBinding>()

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      val navigationMode = navigationModeHolder.navigationMode
      if (navigationMode is NavigationMode.Tabs) {
         val menu = binding.bottomNavigationView.menu
         navigationMode.tabs.forEach { tab ->
            val menuItem = menu.add(0, tab.destinationId, Menu.NONE, tab.title)
            menuItem.setIcon(tab.icon)
         }
         val navHost = childFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
         val navController = navHost.navController
         val graph = navController.navInflater.inflate(destinationsProvider.provideNavigationGraphId())
         graph.setStartDestination(
            navigationMode.startTabDestinationId ?: navigationMode.tabs.first().destinationId
         )
         navController.graph = graph
         NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
      }
   }
}