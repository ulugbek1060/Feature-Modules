package uz.bigboys.sign_in.domain.repositories

interface AuthTokenRepository {


   /**
    * Save the token
    */
   suspend fun setToken(token: String?)

   /**
    * Get the token of current logged-in user.
    */
   suspend fun getToken(): String?
}