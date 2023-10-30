package api.rest.kotlin.credit.application.system.controller

import api.rest.kotlin.credit.application.system.dto.CustomerDTO
import api.rest.kotlin.credit.application.system.dto.CustomerUpdateDTO
import api.rest.kotlin.credit.application.system.dto.CustomerView
import api.rest.kotlin.credit.application.system.entity.Customer
import api.rest.kotlin.credit.application.system.service.impl.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customers")
class CustomerController(
    private val customerService: CustomerService
) {
    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerDTO: CustomerDTO): ResponseEntity<String>{
        val savedCustomer = this.customerService.save(customerDTO.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer ${savedCustomer.email} saved...!")
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id);
        val customerView: CustomerView = CustomerView(customer);
        return ResponseEntity.status(HttpStatus.OK).body(customerView);
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<String> {
        val customer: Customer = this.customerService.findById(id);
        this.customerService.deleteById(customer.id!!);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            "The customer ${customer.email} was deleted..!"
        )
    }

    @PatchMapping
    fun updateCustomer(@RequestParam(value = "customerId")
                       id: Long, @RequestBody @Valid customerUpdateDTO: CustomerUpdateDTO
    ): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id);
        val customerToUpdate: Customer = customerUpdateDTO.toEntity(customer);
        val customerUpdated: Customer = this.customerService.save(customerToUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customerUpdated));
    }
}