package uz.bigboys.sign_up.domain.repositories

import uz.bigboys.sign_up.domain.entity.SignUpData

interface SignUpRepository {

    /**
     * @throws AccountAlreadyExistsException if a user with the provided email already exists
     */
    suspend fun signUp(signUpData: SignUpData)

}