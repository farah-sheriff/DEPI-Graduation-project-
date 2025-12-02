package Data.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 1,
    val name: String,
    val surname: String,
    val birthdate: String
)

