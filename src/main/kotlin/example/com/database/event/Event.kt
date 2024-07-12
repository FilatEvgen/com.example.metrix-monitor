package example.com.database.event

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Timestamp

object Event: Table() {
    private val type = varchar("type", 50)
    private val sourced = varchar("source", 50)
    private val time = long("time")
    private val details = varchar("details", 1500)

    fun insert(eventDTO: EventDTO){
        transaction {
            Event.insert { event ->
                event[type] = eventDTO.type
                event[sourced] = eventDTO.source
                event[time] = eventDTO.time
                event[details] = eventDTO.details
            }
        }
    }
    fun getAllEvent(): List<EventDTO>{
        return transaction {
            Event.selectAll().map { event ->
                EventDTO(
                    event[type],
                    event[sourced],
                    event[time],
                    event[details]
                )
            }
        }
    }

}