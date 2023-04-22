package uz.bigboys.multomodulewithfeatures.glue.orders.mapper

import com.example.orders.domain.entities.Recipient
import uz.bigboys.data.orders.entity.RecipientDataEntity
import javax.inject.Inject

class RecipientMapper @Inject constructor() {

    fun toRecipientDataEntity(recipient: Recipient): RecipientDataEntity {
        return RecipientDataEntity(
            firstName = recipient.firstName,
            lastName = recipient.lastname,
            address = recipient.address,
        )
    }
}