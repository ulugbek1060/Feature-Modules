package uz.bigboys.multomodulewithfeatures.glue.signup

import uz.bigboys.navigation.GlobalNavComponentRouter
import uz.bigboys.sign_up.presentation.SignUpRouter
import javax.inject.Inject

class AdapterSignUpRouter @Inject constructor(
   private val globalNavComponentRouter: GlobalNavComponentRouter
) : SignUpRouter {

   override fun goBack() {
      globalNavComponentRouter.pop()
   }
}