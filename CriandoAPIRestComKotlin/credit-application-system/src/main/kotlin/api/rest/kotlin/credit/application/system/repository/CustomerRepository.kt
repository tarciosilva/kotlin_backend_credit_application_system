package api.rest.kotlin.credit.application.system.repository

import api.rest.kotlin.credit.application.system.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: JpaRepository<Customer, Long> {
}