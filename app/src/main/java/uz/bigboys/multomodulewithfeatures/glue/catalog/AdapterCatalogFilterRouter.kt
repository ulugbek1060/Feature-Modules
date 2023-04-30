package uz.bigboys.multomodulewithfeatures.glue.catalog

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import uz.bigboys.catalog.domain.entities.ProductFilter
import uz.bigboys.catalog.presentation.CatalogFilterRouter
import uz.bigboys.catalog.presentation.filter.CatalogFilterFragment
import uz.bigboys.common.ScreenCommunication
import uz.bigboys.common.listen
import uz.bigboys.multomodulewithfeatures.R
import uz.bigboys.navigation.GlobalNavComponentRouter
import javax.inject.Inject

class AdapterCatalogFilterRouter @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter,
    private val screenCommunication: ScreenCommunication,
) : CatalogFilterRouter {

    override fun launchFilter(initialFilter: ProductFilter) {
        globalNavComponentRouter.launch(
            R.id.catalogFilterFragment,
            CatalogFilterFragment.Screen(initialFilter)
        )
    }

    override fun sendFilterResults(filter: ProductFilter) {
        screenCommunication.publishResult(filter)
    }

    override fun receiveFilterResult(): Flow<ProductFilter> {
        return screenCommunication.listen(ProductFilter::class.java)
    }

    override fun goBack() {
        globalNavComponentRouter.pop()
    }

    override fun registerBackHandler(scope: CoroutineScope, handler: () -> Boolean) {
        globalNavComponentRouter.registerBackHandler(scope, handler)
    }

}