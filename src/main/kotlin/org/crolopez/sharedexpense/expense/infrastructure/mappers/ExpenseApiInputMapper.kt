package org.crolopez.sharedexpense.expense.infrastructure.mappers

import jakarta.inject.Singleton
import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import org.crolopez.sharedexpense.expense.infrastructure.api.dtos.ExpenseDto
import org.crolopez.sharedexpense.shared.infrastructure.api.dtos.DataDto
import org.crolopez.sharedexpense.shared.infrastructure.mappers.Mapper
import org.crolopez.sharedexpense.user.domain.entities.UserEntity
import org.crolopez.sharedexpense.user.infrastructure.api.dtos.UserDto
import java.time.Instant
import javax.validation.constraints.NotNull

@Singleton
class ExpenseApiInputMapper: Mapper<ExpenseDto, ExpenseEntity> {

    private val dataType: String = "expense"

    override fun convert(entity: ExpenseDto): ExpenseEntity {
        return ExpenseEntity(
            amount = entity.amount,
            description = entity.description
        )
    }
}