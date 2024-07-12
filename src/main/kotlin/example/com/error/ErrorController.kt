package example.com.error

import example.com.database.error.Error
import example.com.database.error.ErrorDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class ErrorController(private val applicationCall: ApplicationCall) {
    suspend fun recordError(){
        try {
            val receive = applicationCall.receive<GettingError>()
            val errorDTO = receiveToDTO(receive)
            Error.insert(errorDTO)
            applicationCall.respond(HttpStatusCode.OK)
        } catch (e: Exception){
            applicationCall.respond(HttpStatusCode.BadRequest, e.localizedMessage)
        }
    }
    private fun receiveToDTO(gettingError: GettingError): ErrorDTO{
        return ErrorDTO(
            gettingError.type,
            gettingError.source,
            gettingError.time,
            gettingError.description
        )
    }
}