package example.com.database.event

data class EventDTO(
    val type: String,
    val source: String,
    val time: Long,
    val details: String,
)