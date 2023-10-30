package api.rest.kotlin.credit.application.system.dto

import api.rest.kotlin.credit.application.system.entity.Customer
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDTO(
    @field:NotEmpty(message = "This field must be filled..!") val firstName: String,
    @field:NotEmpty(message = "This field must be filled..!") val lastName: String,
    @field:NotEmpty(message = "This field must be filled..!") @field:CPF val cpf: String,
    @field:NotNull(message = "This field must be filled..!") val income: BigDecimal,
    @field:NotEmpty(message = "This field must be filled..!")
    @field:Email(message = "Invalid email..!")
    val email: String,
    @field:NotEmpty(message = "This field must be filled..!") val password: String,
    @field:NotEmpty(message = "This field must be filled..!") val zipCode: String,
    @field:NotEmpty(message = "This field must be filled..!") val street: String
) {
    fun toEntity(): Customer {
        val customer: Customer = Customer();
        customer.firstName = this.firstName;
        customer.lastName = this.lastName;
        customer.email = this.email;
        customer.password = this.password;
        customer.address.street = this.street;
        customer.address.zipCode = this.zipCode;
        customer.cpf = this.cpf;
        customer.income = this.income
        return customer
    }
}
