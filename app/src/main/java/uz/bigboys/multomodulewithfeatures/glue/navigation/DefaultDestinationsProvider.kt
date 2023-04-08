package uz.bigboys.multomodulewithfeatures.glue.navigation

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.bigboys.multomodulewithfeatures.R
import uz.bigboys.navigation.DestinationsProvider
import uz.bigboys.navigation.presentation.navigation.NavTab
import javax.inject.Inject

class DefaultDestinationsProvider
@Inject constructor(
   @ApplicationContext private val context: Context
) : DestinationsProvider {

   override fun provideStartDestinationId(): Int {
      return R.id.signInFragment
   }

   override fun provideNavigationGraphId(): Int {
      return R.navigation.nav_graph
   }

   override fun provideMainTabs(): List<NavTab> {
      return listOf(
//         NavTab(
//            destinationId = R.id.catalogFragment,
//            title = context.getString(R.string.tab_catalog),
//            icon = R.drawable.ic_catalog,
//         ),
//         NavTab(
//            destinationId = R.id.ordersListFragment,
//            title = context.getString(R.string.tab_orders),
//            icon = R.drawable.ic_orders,
//         ),
//         NavTab(
//            destinationId = R.id.profileFragment,
//            title = context.getString(R.string.tab_profile),
//            icon = R.drawable.ic_profile,
//         )
      )
   }

   override fun provideTabsDestinationId(): Int {
//      return R.id.tabsFragment
      return 0
   }

   override fun provideCartDestinationId(): Int {
//      return R.id.cartListFragment
      return 0
   }
}