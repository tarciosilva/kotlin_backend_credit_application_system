package api.rest.kotlin.credit.application.system.service

import api.rest.kotlin.credit.application.system.entity.Customer

interface ICustomerService {
    fun save(customer: Customer): Customer
    fun findById(id: Long): Customer
    fun deleteById(id: Long)
}