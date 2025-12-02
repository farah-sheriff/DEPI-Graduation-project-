package Data.dao

import Data.Model.UserEntity
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE id = 1 LIMIT 1")
    suspend fun getUser(): UserEntity?

    @Query("SELECT * FROM user WHERE id = 1 LIMIT 1")
    fun getUserFlow(): Flow<UserEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)
}

