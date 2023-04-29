package uz.bigboys.multomodulewithfeatures.glue.catalog.mappers

import com.example.catalog.domain.entities.SortOrder
import uz.bigboys.data.products.entities.SortOrderDataValue
import javax.inject.Inject

class SortOrderMapper @Inject constructor() {

    fun toSortOrderDataValue(sortOrder: SortOrder): SortOrderDataValue {
        return when (sortOrder) {
            SortOrder.DESC -> SortOrderDataValue.DESC
            SortOrder.ASC -> SortOrderDataValue.ASC
        }
    }

}