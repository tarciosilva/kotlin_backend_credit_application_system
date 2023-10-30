package api.rest.kotlin.credit.application.system.dto

import api.rest.kotlin.credit.application.system.entity.Customer
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class CustomerUpdateDTO(
    @field:NotEmpty(message = "This field must be filled..!") val firstName: String,
    @field:NotEmpty(message = "This field must be filled..!") val lastName: String,
    @field:NotNull(message = "This field must be filled..!") val income: BigDecimal,
    @field:NotEmpty(message = "This field must be filled..!") val zipCode: String,
    @field:NotEmpty(message = "This field must be filled..!") val street: String
) {
    fun toEntity(customer: Customer): Customer{
        customer.firstName = this.firstName;
        customer.lastName = this.lastName;
        customer.income = this.income;
        customer.address.zipCode = this.zipCode;
        customer.address.street = this.street;
        return customer;
    }

}
