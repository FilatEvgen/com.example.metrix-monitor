package example.com.error

import example.com.Endpoints.POST_ERROR
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureErrorRouting(){
    routing {
        post(POST_ERROR){
            val controller = ErrorController(call)
            controller.recordError()
        }
    }
}