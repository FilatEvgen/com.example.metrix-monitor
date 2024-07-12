package example.com.error

import kotlinx.serialization.Serializable

@Serializable
data class GettingError(
    val type: String,
    val source: String,
    val time: Long,
    val description: String,
)

