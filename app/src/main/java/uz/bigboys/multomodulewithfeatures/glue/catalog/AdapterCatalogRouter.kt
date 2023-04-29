package uz.bigboys.multomodulewithfeatures.glue.catalog


import com.example.catalog.presentation.CatalogRouter
import com.example.catalog.presentation.details.ProductDetailsFragment
import uz.bigboys.multomodulewithfeatures.R
import uz.bigboys.navigation.GlobalNavComponentRouter
import javax.inject.Inject

class AdapterCatalogRouter @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter,
) : CatalogRouter {

    override fun launchDetails(productId: Long) {
        globalNavComponentRouter.launch(
            R.id.productDetailsFragment,
            ProductDetailsFragment.Screen(productId)
        )
    }

    override fun launchCreateOrder() {
        globalNavComponentRouter.launch(R.id.createOrderFragment)
    }

}