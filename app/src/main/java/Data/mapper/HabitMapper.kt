package Data.mapper




import Data.Model.HabitEntity
import androidx.compose.ui.graphics.Color
import com.example.habittracker.domain.model.Habit

fun HabitEntity.toDomain(): Habit {
    return Habit(
        id = id,
        title = title,
        note = note,
        duration = duration,
        notificationsEnabled = notificationsEnabled,
        tag = tag,
        tagColor = Color(tagColor),
        time = time,
        isChecked = isChecked
    )
}

fun Habit.toEntity(): HabitEntity {
    return HabitEntity(
        id = id,
        title = title,
        note = note,
        duration = duration,
        notificationsEnabled = notificationsEnabled,
        tag = tag,
        tagColor = tagColor.value.toLong(),
        time = time,
        isChecked = isChecked
    )
}

