package uz.bigboys.multomodulewithfeatures.glue.signin

import uz.bigboys.multomodulewithfeatures.R
import uz.bigboys.navigation.GlobalNavComponentRouter
import uz.bigboys.sign_in.presentation.SignInRouter
import uz.bigboys.sign_up.presentation.signup.SignUpFragment
import javax.inject.Inject

class AdapterSignInRouter @Inject constructor(
   private val globalNavComponentRouter: GlobalNavComponentRouter
) : SignInRouter {
   override fun launchSignUp(email: String) {
      val screen = SignUpFragment.Screen(email)
      globalNavComponentRouter.launch(R.id.signUpFragment, screen)
   }

   override fun launchMain() {
      globalNavComponentRouter.startTabs()
   }
}