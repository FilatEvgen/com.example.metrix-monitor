package example.com.anomaley

import example.com.Endpoints.WS_ERROR_SUBSCRIBE
import example.com.utils.minutes
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

fun Application.configureAnomalyRouting() {
    routing {
        webSocket(WS_ERROR_SUBSCRIBE) {
            val controller = AnomaleyController()
            while (isActive) {
                val (anomalies, errorFile) = controller.checkForAnomalies()!!
                send(Frame.Text(anomalies))
                send(Frame.Binary(true, errorFile.readBytes()))
                errorFile.delete()
                delay(30.minutes())
            }
        }
    }
}