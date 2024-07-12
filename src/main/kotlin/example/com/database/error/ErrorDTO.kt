package example.com.database.error

data class ErrorDTO(
    val type: String,
    val source: String,
    val time: Long,
    val description: String,
)