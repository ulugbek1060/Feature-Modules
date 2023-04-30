package uz.bigboys.multomodulewithfeatures.glue.profile.repositories

import uz.bigboys.profile.domain.entities.Profile
import uz.bigboys.profile.domain.repositories.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.bigboys.common.Container
import uz.bigboys.data.AccountsDataRepository
import uz.bigboys.multomodulewithfeatures.fromatter.DateTimeFormatter
import javax.inject.Inject

class AdapterProfileRepository @Inject constructor(
    private val accountsDataRepository: AccountsDataRepository,
    private val dateTimeFormatter: DateTimeFormatter,
) : ProfileRepository {

    override fun getProfile(): Flow<Container<Profile>> {
        return accountsDataRepository.getAccount().map { container ->
            container.map {
                Profile(
                    id = it.id,
                    username = it.username,
                    email = it.email,
                    createdAtString = dateTimeFormatter.formatDateTime(it.createdAtMillis)
                )
            }
        }
    }

    override fun reload() {
        accountsDataRepository.reload()
    }

    override suspend fun editUsername(newUsername: String) {
        accountsDataRepository.setAccountUsername(newUsername)
    }

}