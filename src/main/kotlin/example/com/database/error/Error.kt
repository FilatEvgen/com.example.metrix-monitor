package example.com.database.error

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Error : Table() {
    // Определяем столбы и их размеры для хранения
    private val type = varchar("type", 50)
    private val sourced = varchar("source", 50)
    private val time = long("time")
    private val description = varchar("description", 1500)
    // функция принимает объект ErrorDTO и вставляет в таблицу error, транзакция нужна для целостности данных
    fun insert(errorDTO: ErrorDTO){
        transaction {
            Error.insert { error ->
                error[type] = errorDTO.type
                error[sourced] = errorDTO.source
                error[time] = errorDTO.time
                error[description] = errorDTO.description

            }
        }
    }
    // функция извлекает все записи из таблицы error и возвращает их в виде списка объектов ErrorDTO, выводит количество ошибок в консоль
    fun getAllError(): List<ErrorDTO>{
        val errors = transaction {
            Error.selectAll().map { error ->
                ErrorDTO(
                    error[type],
                    error[sourced],
                    error[time],
                    error[description]
                )
            }
        }
        println("Error count: ${errors.size}")
        return errors
    }
}