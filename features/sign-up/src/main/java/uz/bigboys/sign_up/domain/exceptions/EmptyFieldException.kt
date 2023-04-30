package uz.bigboys.sign_up.domain.exceptions

import uz.bigboys.common.AppException
import uz.bigboys.sign_up.domain.entity.SignUpField

class EmptyFieldException(
   val field: SignUpField,
) : AppException()