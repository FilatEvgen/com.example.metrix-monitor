package example.com.event

import example.com.Endpoints.GET_ALL_EVENTS
import example.com.Endpoints.POST_EVENT
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureEventRouting() {
    routing {
        post(POST_EVENT) {
            val controller = EventController(call)
            controller.recordEvent()
        }
        get(GET_ALL_EVENTS) {
            val controller = EventController(call)
            controller.getTopEvent()
        }
    }
}