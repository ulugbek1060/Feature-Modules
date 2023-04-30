package uz.bigboys.sign_up.presentation.signup

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import uz.bigboys.presentation.BaseViewModel
import uz.bigboys.sign_up.R
import uz.bigboys.sign_up.domain.SignUpUseCase
import uz.bigboys.sign_up.domain.entity.SignUpData
import uz.bigboys.sign_up.domain.entity.SignUpField
import uz.bigboys.sign_up.domain.exceptions.AccountAlreadyExistsException
import uz.bigboys.sign_up.domain.exceptions.EmptyFieldException
import uz.bigboys.sign_up.domain.exceptions.PasswordMismatchException
import uz.bigboys.sign_up.presentation.SignUpRouter

class SignUpViewModel @AssistedInject constructor(
   @Assisted private val screen: SignUpFragment.Screen,
   private val router: SignUpRouter,
   private val signUpUseCase: SignUpUseCase,
) : BaseViewModel() {

   private val isSignUpInProgressFlow = MutableStateFlow(false)
   private val fieldErrorMessageFlow = MutableStateFlow<Pair<SignUpField, String>?>(null)

   val focusFieldLiveEventValue = liveEvent<SignUpField>()
   val clearFieldLiveEventValue = liveEvent<SignUpField>()
   val initialEmailLiveEventValue = liveEvent<String>()

   val stateLiveValue = combine(
      isSignUpInProgressFlow,
      fieldErrorMessageFlow,
      ::State
   )
      .toLiveValue()

   init {
      initialEmailLiveEventValue.publish(screen.email)
   }

   fun signUp(signUpData: SignUpData) = debounce {
      viewModelScope.launch {
         try {
            showProgress()
            signUpUseCase.signUp(signUpData)
            commonUi.toast(resources.getString(R.string.signup_account_created))
            router.goBack()
         } catch (e: EmptyFieldException) {
            handleEmptyFieldException(e)
         } catch (e: PasswordMismatchException) {
            handlePasswordMismatchException()
         } catch (e: AccountAlreadyExistsException) {
            handleAccountAlreadyExistException()
         } finally {
            hideProgress()
         }
      }
   }

   fun clearError(field: SignUpField) {
      val currentErrorField = fieldErrorMessageFlow.value?.first
      if (currentErrorField == field) {
         fieldErrorMessageFlow.value = null
      }
   }

   private fun handleEmptyFieldException(emptyFieldException: EmptyFieldException) {
      focusField(emptyFieldException.field)
      setFieldError(emptyFieldException.field, resources.getString(R.string.signup_empty_field))
   }

   private fun handlePasswordMismatchException() {
      focusField(SignUpField.REPEAT_PASSWORD)
      clearField(SignUpField.REPEAT_PASSWORD)
      setFieldError(
         SignUpField.REPEAT_PASSWORD,
         resources.getString(R.string.signup_password_mismatch)
      )
   }

   private fun handleAccountAlreadyExistException() {
      focusField(SignUpField.EMAIL)
      setFieldError(SignUpField.EMAIL, resources.getString(R.string.signup_account_exists))
   }

   private fun showProgress() {
      isSignUpInProgressFlow.value = true
   }

   private fun hideProgress() {
      isSignUpInProgressFlow.value = false
   }

   private fun focusField(field: SignUpField) {
      focusFieldLiveEventValue.publish(field)
   }

   private fun clearField(field: SignUpField) {
      clearFieldLiveEventValue.publish(field)
   }

   private fun setFieldError(field: SignUpField, errorMessage: String) {
      fieldErrorMessageFlow.value = field to errorMessage
   }

   data class State(
      val signUpInProgress: Boolean,
      val fieldErrorMessage: Pair<SignUpField, String>?
   ) {
      val showProgressBar: Boolean get() = signUpInProgress
      val enableSignUpButton: Boolean get() = !signUpInProgress
   }

   @AssistedFactory
   interface Factory {
      fun create(
         screen: SignUpFragment.Screen,
      ): SignUpViewModel
   }

}