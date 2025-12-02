package Data.Reprosoitry

import Data.database.HabitDatabase
import Data.mapper.toDomain
import Data.mapper.toEntity
import Domain.Model.User
import Domain.Repository.UserRepository



import android.content.Context


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(context: Context) : UserRepository {

    private val database = HabitDatabase.getDatabase(context)
    private val userDao = database.userDao()

    override fun getUser(): Flow<User?> {
        return userDao.getUserFlow().map { it?.toDomain() }
    }

    override suspend fun getUserSync(): User? {
        return userDao.getUser()?.toDomain()
    }

    override suspend fun saveUser(user: User) {
        userDao.insertUser(user.toEntity())
    }

    override suspend fun deleteUser() {
        userDao.getUser()?.let { userDao.deleteUser(it) }
    }
}

