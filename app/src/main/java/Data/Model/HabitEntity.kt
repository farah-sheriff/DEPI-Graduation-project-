package Data.Model



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class HabitEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val note: String = "",
    val duration: Int = 30,
    val notificationsEnabled: Boolean = true,
    val tag: String = "New",
    val tagColor: Long = 0xFFFF4081,
    val time: String = "",
    val isChecked: Boolean = false
)

