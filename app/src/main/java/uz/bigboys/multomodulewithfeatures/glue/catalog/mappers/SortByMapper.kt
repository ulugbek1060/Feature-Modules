package uz.bigboys.multomodulewithfeatures.glue.catalog.mappers

import com.example.catalog.domain.entities.SortBy
import uz.bigboys.data.products.entities.SortByDataValue
import javax.inject.Inject

class SortByMapper @Inject constructor() {

    fun toSortByDataValue(sortBy: SortBy): SortByDataValue {
        return when (sortBy) {
            SortBy.PRICE -> SortByDataValue.PRICE
            SortBy.NAME -> SortByDataValue.NAME
        }
    }

}