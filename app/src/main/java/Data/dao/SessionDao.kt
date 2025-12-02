package Data.dao

import Data.Model.SessionEntity




import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {

    @Query("SELECT * FROM sessions WHERE habitId = :habitId ORDER BY date DESC")
    fun getSessionsByHabitId(habitId: Long): Flow<List<SessionEntity>>

    @Query("SELECT * FROM sessions WHERE habitId = :habitId ORDER BY date DESC")
    suspend fun getSessionsByHabitIdSync(habitId: Long): List<SessionEntity>

    @Query("SELECT * FROM sessions ORDER BY date DESC")
    suspend fun getAllSessionsSync(): List<SessionEntity>

    @Query("SELECT * FROM sessions ORDER BY date DESC")
    fun getAllSessions(): Flow<List<SessionEntity>>

    @Insert
    suspend fun insertSession(session: SessionEntity): Long

    @Delete
    suspend fun deleteSession(session: SessionEntity)

    @Query("DELETE FROM sessions WHERE habitId = :habitId")
    suspend fun deleteSessionsByHabitId(habitId: Long)
}


