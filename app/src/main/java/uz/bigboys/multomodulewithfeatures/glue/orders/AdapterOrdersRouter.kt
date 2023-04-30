package uz.bigboys.multomodulewithfeatures.glue.orders

import uz.bigboys.orders.presentation.OrdersRouter
import uz.bigboys.multomodulewithfeatures.R
import uz.bigboys.navigation.GlobalNavComponentRouter
import javax.inject.Inject

class AdapterOrdersRouter @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter
) : OrdersRouter {

    override fun launchOrdersTab() {
        globalNavComponentRouter.startTabs(startTabDestinationId = R.id.ordersListFragment)
    }

}