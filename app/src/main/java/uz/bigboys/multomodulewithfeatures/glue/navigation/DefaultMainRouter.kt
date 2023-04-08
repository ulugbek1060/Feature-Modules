package uz.bigboys.multomodulewithfeatures.glue.navigation

import uz.bigboys.multomodulewithfeatures.R
import uz.bigboys.navigation.GlobalNavComponentRouter
import uz.bigboys.navigation.presentation.MainRouter
import javax.inject.Inject

class DefaultMainRouter @Inject constructor(
   private val navControllerRouter: GlobalNavComponentRouter
) : MainRouter {

   override fun launchCart() {
      navControllerRouter.launch(0)//R.id.cartListFragment)
   }
}