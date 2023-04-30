package uz.bigboys.orders.domain.exceptions

import uz.bigboys.orders.domain.entities.Field
import uz.bigboys.common.AppException

class EmptyFieldException(val field: Field) : AppException()