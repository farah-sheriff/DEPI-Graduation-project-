package Domain.Model

data class Session(
    val id: Long = 0,
    val habitId: Long,
    val date: Long = System.currentTimeMillis(),
    val duration: Int = 0
)

