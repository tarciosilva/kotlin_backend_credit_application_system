package api.rest.kotlin.credit.application.system.repository

import api.rest.kotlin.credit.application.system.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository //Opcional por conta do JpaRepository //Pesquisar DTO's
interface CreditRepository: JpaRepository<Credit, Long> {
    fun findByCreditCode(creditCode: UUID): Credit?

    @Query(value = "SELECT * FROM CREDIT WHERE CUSTOMER_ID = ?1", nativeQuery = true)
    fun finAllByCustomerId(customerId: Long): List<Credit>
}