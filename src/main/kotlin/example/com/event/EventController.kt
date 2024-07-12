package example.com.event

import example.com.database.event.Event
import example.com.database.event.EventDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class EventController(private val applicationCall: ApplicationCall) {
    suspend fun recordEvent() {
        try {
            val receive = applicationCall.receive<GettingEvent>()
            val eventDTO = receiveToDTO(receive)
            Event.insert(eventDTO)
            applicationCall.respond(HttpStatusCode.OK)
        } catch (e: Exception) {
            applicationCall.respond(HttpStatusCode.BadRequest, e.localizedMessage)
        }
    }

    suspend fun getTopEvent() {
        try {
            val eventsDto = Event.getAllEvent()
            val groupedEvents = eventsDto.groupingBy { it.details }.eachCount()
            val combinedEvents = groupedEvents.map { "${it.key} - ${it.value}" }
            val sortedEvents = combinedEvents.sortedByDescending { it.split(" - ")[1].toInt() }
            applicationCall.respond(sortedEvents)
        } catch (e: Exception) {
            applicationCall.respond(HttpStatusCode.BadRequest, e.localizedMessage)
        }
    }

    private fun receiveToDTO(eventReceive: GettingEvent): EventDTO {
        return EventDTO(
            eventReceive.type,
            eventReceive.source,
            eventReceive.time,
            eventReceive.details
        )
    }
}