package org.crolopez.sharedexpense.group.infrastructure.repositories.entities

import io.micronaut.data.jdbc.annotation.ColumnTransformer
import org.crolopez.sharedexpense.user.infrastructure.repositories.entities.UserDbEntity
import java.time.Clock
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.persistence.*

@Entity
@Table(name = "EXPENSES")
@SequenceGenerator(name = "expense_sequence_gen", sequenceName = "expense_sequence_gen_", initialValue = 100, allocationSize = 1)
data class ExpenseDbEntity(
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expense_sequence_gen")
    val id: Long = 0,

    @Column(name = "amount", nullable = false)
    val amount: Double = 0.0,

    @Column(name = "currency", nullable = false)
    val currency: String = "",

    @Column(name = "date_created")
    var dateCreated: Long = 0,

    @Column(name = "description", nullable = false)
    val description: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "e_group")
    val group: GroupDbEntity? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "e_user")
    val user: UserDbEntity? = null
) {
    @PrePersist
    fun setDateNow() {
        dateCreated = Instant.now(Clock.systemUTC()).truncatedTo(ChronoUnit.SECONDS).epochSecond
    }
}