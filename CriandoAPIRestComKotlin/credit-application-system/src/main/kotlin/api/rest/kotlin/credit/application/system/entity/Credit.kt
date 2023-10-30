package api.rest.kotlin.credit.application.system.entity

import api.rest.kotlin.credit.application.system.enummeration.Status
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@Entity
class Credit {
    @Column(nullable = false, unique = true)
    val creditCode: UUID = UUID.randomUUID()
    @Column(nullable = false)
    var creditValue: BigDecimal = BigDecimal.ZERO
    @Column(nullable = false)
    var dayFirstInstallment: LocalDate = LocalDate.now()
    @Column(nullable = false)
    var numberOfInstallments: Int = 0
    @Column(nullable = false)
    @Enumerated
    val status: Status = Status.IN_PROGRESS
    @ManyToOne var customer: Customer? = null
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
