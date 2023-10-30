package api.rest.kotlin.credit.application.system.controller

import api.rest.kotlin.credit.application.system.dto.CreditDTO
import api.rest.kotlin.credit.application.system.dto.CreditView
import api.rest.kotlin.credit.application.system.dto.CreditViewList
import api.rest.kotlin.credit.application.system.entity.Credit
import api.rest.kotlin.credit.application.system.service.impl.CreditService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/credits")
class CreditController(
    private val creditService: CreditService
) {
    @PostMapping
    fun save(@RequestBody @Valid creditDTO: CreditDTO): ResponseEntity<String> {
        val creditSaved: Credit = this.creditService.save(creditDTO.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(
            "Credit ${creditSaved.id} for Customer ${creditSaved.customer?.firstName} was saved successfully"
        )
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): ResponseEntity<List<CreditViewList>>
         = ResponseEntity.status(HttpStatus.OK).body(
        this.creditService
            .findAllByCustomer(customerId).map { credit: Credit -> CreditViewList(credit) }.toList()
         )

    @GetMapping("/{creditCode}")
    fun findByCreditCode(@RequestParam(value = "customerID") customerId: Long, @PathVariable creditCode: UUID): ResponseEntity<CreditView> {
        val credit: Credit = this.creditService.findByCreditCode(customerId, creditCode)
        return ResponseEntity.status(HttpStatus.OK).body(CreditView(credit))
    }
}