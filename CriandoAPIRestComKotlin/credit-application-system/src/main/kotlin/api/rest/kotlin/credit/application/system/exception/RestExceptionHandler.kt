package api.rest.kotlin.credit.application.system.exception

import org.hibernate.exception.DataException
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDate

@RestControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ExceptionDetails>{
        val erros: MutableMap<String, String?> = HashMap();
        ex.bindingResult.allErrors.stream().forEach{
            error: ObjectError ->
            val fieldName: String = (error as FieldError).field
            val messageError: String? = error.defaultMessage
            erros[fieldName] = messageError
        }
        return ResponseEntity(
            ExceptionDetails(
                title = "Bad Request, consult the documentation for more details..",
                timeStamp = LocalDate.now(),
                status = HttpStatus.BAD_REQUEST.value().toString(),
                exception = ex.javaClass.toString(),
                details = erros
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(DataAccessException::class)
    fun handleNotValidAccess(ex: DataAccessException): ResponseEntity<ExceptionDetails> =
          ResponseEntity.status(HttpStatus.CONFLICT).body(
              ExceptionDetails(
                  title = "Conflict, consult the documentation for more details..",
                  timeStamp = LocalDate.now(),
                  status = HttpStatus.BAD_REQUEST.value().toString(),
                  exception = ex.javaClass.toString(),
                  details = mutableMapOf(ex.cause.toString() to ex.message)
              )
          )
}