package example.com

import example.com.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database

fun main() {
    Database.connect("jdbc:postgresql://127.0.0.1:5432/weatherbd", "org.postgresql.Driver", "postgres", "12345q")
    embeddedServer(Netty, port = 8082, host = "127.0.0.1", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSockets()
    configureSerialization()
    configureRouting()
}
