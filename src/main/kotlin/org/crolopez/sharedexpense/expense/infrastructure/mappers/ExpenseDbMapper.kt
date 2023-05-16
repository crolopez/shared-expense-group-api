package org.crolopez.sharedexpense.expense.infrastructure.mappers

import jakarta.inject.Singleton
import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import org.crolopez.sharedexpense.group.infrastructure.repositories.entities.ExpenseDbEntity
import org.crolopez.sharedexpense.shared.infrastructure.mappers.Mapper
import java.time.Instant

@Singleton
class ExpenseDbMapper: Mapper<ExpenseDbEntity, ExpenseEntity> {

    override fun convert(entity: ExpenseDbEntity): ExpenseEntity {
        return ExpenseEntity(
            id = entity.id,
            amount = entity.amount,
            currency = entity.currency,
            dateCreated = Instant.ofEpochSecond(entity.dateCreated),
            description = entity.description,
            username = entity.user!!.username,
            user = entity.user.name
        )
    }
}