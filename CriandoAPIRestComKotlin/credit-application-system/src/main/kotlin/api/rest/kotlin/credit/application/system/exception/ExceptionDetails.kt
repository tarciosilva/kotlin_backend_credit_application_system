package api.rest.kotlin.credit.application.system.exception

import java.time.LocalDate

data class ExceptionDetails(
    val title: String,
    val timeStamp: LocalDate,
    val status: String,
    val exception: String,
    val details: MutableMap<String, String?>
) {
}