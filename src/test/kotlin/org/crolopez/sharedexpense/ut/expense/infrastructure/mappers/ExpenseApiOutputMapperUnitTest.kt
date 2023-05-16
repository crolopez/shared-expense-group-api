package org.crolopez.sharedexpense.ut.expense.infrastructure.mappers

import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import org.crolopez.sharedexpense.expense.infrastructure.api.dtos.ExpenseDto
import org.crolopez.sharedexpense.expense.infrastructure.mappers.ExpenseApiOutputMapper
import org.crolopez.sharedexpense.shared.infrastructure.api.dtos.DataDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ExpenseApiOutputMapperUnitTest {

    private lateinit var expenseApiOutputMapper: ExpenseApiOutputMapper

    @BeforeEach
    fun setup() {
        expenseApiOutputMapper = ExpenseApiOutputMapper()
    }

    @Test
    fun givenExpenseEntity_whenConvert_thenReturnDataDtoWithCorrectAttributes() {
        val expenseEntity = ExpenseEntity(
            amount = 100.0,
            description = "Expense Description"
        )

        val dataDto = expenseApiOutputMapper.convert(expenseEntity)

        assertEquals("expense", dataDto.type)
        val expenseDto = dataDto.attributes
        assertEquals(100.0, expenseDto.amount)
        assertEquals("Expense Description", expenseDto.description)
        assertEquals(expenseEntity.dateCreated.toString(), expenseDto.dateCreated)
    }
}
