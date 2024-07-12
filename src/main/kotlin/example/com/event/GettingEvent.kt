package example.com.event

import kotlinx.serialization.Serializable

@Serializable
data class GettingEvent(
    val type: String,
    val source: String,
    val time: Long,
    val details: String,
)

