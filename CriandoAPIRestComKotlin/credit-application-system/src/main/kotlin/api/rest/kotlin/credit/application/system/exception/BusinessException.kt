package api.rest.kotlin.credit.application.system.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDate

class BusinessException(override val message: String?): RuntimeException(message){
    @ExceptionHandler(BusinessException::class)
    fun handleNotValidBusiness(ex: BusinessException): ResponseEntity<ExceptionDetails> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ExceptionDetails(
                title = "Bad Request, consult the documentation for more details..",
                timeStamp = LocalDate.now(),
                status = HttpStatus.BAD_REQUEST.value().toString(),
                exception = ex.javaClass.toString(),
                details = mutableMapOf(ex.cause.toString() to ex.message)
            )
        )
}
