package uz.bigboys.sign_up.domain.exceptions

import uz.bigboys.common.AppException


class AccountAlreadyExistsException(
   cause: Throwable? = null
) : AppException(cause = cause)