package api.rest.kotlin.credit.application.system.service.impl

import api.rest.kotlin.credit.application.system.entity.Credit
import api.rest.kotlin.credit.application.system.repository.CreditRepository
import api.rest.kotlin.credit.application.system.service.ICreditService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
): ICreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService
                .findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)

    }


    override fun findAllByCustomer(customerID: Long): List<Credit> =
        this.creditRepository.finAllByCustomerId(customerID)

    override fun findByCreditCode(customerID: Long, creditCode: UUID): Credit {
        val credit = this.creditRepository.findByCreditCode(creditCode)
            ?: throw RuntimeException("This code ($creditCode) does not exists..!");
        return if (credit.customer?.id == customerID) credit
        else throw RuntimeException("Contact the admin..");
    }
}