package org.crolopez.sharedexpense.expense.infrastructure.mappers

import jakarta.inject.Singleton
import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import org.crolopez.sharedexpense.expense.infrastructure.api.dtos.ExpenseDto
import org.crolopez.sharedexpense.shared.infrastructure.api.dtos.DataDto
import org.crolopez.sharedexpense.shared.infrastructure.mappers.Mapper
import org.crolopez.sharedexpense.user.domain.entities.UserEntity
import org.crolopez.sharedexpense.user.infrastructure.api.dtos.UserDto
import java.util.*
import javax.validation.constraints.NotNull

@Singleton
class ExpenseApiOutputMapper: Mapper<ExpenseEntity, DataDto<ExpenseDto>> {

    private val dataType: String = "expense"

    override fun convert(entity: ExpenseEntity): DataDto<ExpenseDto> {
        return DataDto(
            type = dataType,
            id = entity.id.toString(),
            attributes = ExpenseDto(
                amount = entity.amount,
                description = entity.description,
                dateCreated = entity.dateCreated.toString(),
                currency = entity.currency
            )
        )
    }
}