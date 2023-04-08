package uz.bigboys.sign_in.presentation

interface SignInRouter {

   /**
    * Launch Sign-Up screen.
    * @param email pre-fill email field on the sign-up screen
    */
   fun launchSignUp(email:String = "")

   /**
    * Launch main tabs for already logged-in user.
    */
   fun launchMain()
}