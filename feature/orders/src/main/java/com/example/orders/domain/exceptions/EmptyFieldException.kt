package com.example.orders.domain.exceptions

import com.example.orders.domain.entities.Field
import uz.bigboys.common.AppException

class EmptyFieldException(field: Field) : AppException()