package example.com.plugins

import example.com.anomaley.configureAnomalyRouting
import example.com.error.configureErrorRouting
import example.com.event.configureEventRouting
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    configureErrorRouting()
    configureEventRouting()
    configureAnomalyRouting()

}
