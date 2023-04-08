package uz.bigboys.sign_in.domain.repositories

interface AuthServiceRepository {

   /**
    * Exchange the email-password pair to the auth token
    */
   suspend fun signIn(email: String, password: String): String
}