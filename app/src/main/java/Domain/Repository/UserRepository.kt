package Domain.Repository

import Domain.Model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(): Flow<User?>
    suspend fun getUserSync(): User?
    suspend fun saveUser(user: User)
    suspend fun deleteUser()
}

