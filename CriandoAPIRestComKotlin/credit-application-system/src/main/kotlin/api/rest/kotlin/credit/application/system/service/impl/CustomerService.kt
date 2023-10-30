package api.rest.kotlin.credit.application.system.service.impl

import api.rest.kotlin.credit.application.system.entity.Customer
import api.rest.kotlin.credit.application.system.exception.BusinessException
import api.rest.kotlin.credit.application.system.repository.CustomerRepository
import api.rest.kotlin.credit.application.system.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
): ICustomerService {
    override fun save(customer: Customer): Customer =
        this.customerRepository.save(customer)


    override fun findById(id: Long): Customer =
        this.customerRepository.findById(id).orElseThrow{
            throw BusinessException("Id $id not found..!")
    };


    override fun deleteById(id: Long){
        this.customerRepository.deleteById(id)
    }
}


