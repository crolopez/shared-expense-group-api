package org.crolopez.sharedexpense.expense.infrastructure.mappers

import jakarta.inject.Singleton
import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import org.crolopez.sharedexpense.expense.infrastructure.api.dtos.ExpenseDto
import org.crolopez.sharedexpense.shared.infrastructure.api.dtos.DataDto
import org.crolopez.sharedexpense.shared.infrastructure.mappers.Mapper

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
                currency = entity.currency,
                user = entity.user
            )
        )
    }
}