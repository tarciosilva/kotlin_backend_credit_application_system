package api.rest.kotlin.credit.application.system.dto

import api.rest.kotlin.credit.application.system.entity.Credit
import api.rest.kotlin.credit.application.system.service.impl.CustomerService
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDate

class CreditDTO(
   @field:NotNull(message = "This field must be filled..!") private var creditValue: BigDecimal,
   @field:Future(message = "Invalid Date..!") private var dayFirstOfInstallment: LocalDate,
   @field:Max(10) @field:Min(1) private var numberOfInstallments: Int,
   private var customerId: Long,
   private val customerService: CustomerService
) {
    fun toEntity(): Credit {
        val credit: Credit = Credit();
        credit.creditValue = this.creditValue;
        credit.dayFirstInstallment = this.dayFirstOfInstallment;
        credit.numberOfInstallments = this.numberOfInstallments;
        credit.customer = this.customerService.findById(this.customerId)
        return credit;
    }
}
