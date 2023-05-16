package org.crolopez.sharedexpense.ut.expense.infrastructure.mappers

import org.crolopez.sharedexpense.expense.infrastructure.api.dtos.ExpenseDto
import org.crolopez.sharedexpense.expense.infrastructure.mappers.ExpenseApiInputMapper
import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ExpenseApiInputMapperUnitTest {

    private lateinit var expenseApiInputMapper: ExpenseApiInputMapper

    @BeforeEach
    fun setup() {
        expenseApiInputMapper = ExpenseApiInputMapper()
    }

    @Test
    fun givenExpenseDto_whenConvert_thenReturnExpenseEntityWithCorrectAttributes() {
        val expenseDto = ExpenseDto(amount = 100.0, description = "Expense Description")

        val expenseEntity = expenseApiInputMapper.convert(expenseDto)

        assertEquals(100.0, expenseEntity.amount)
        assertEquals("Expense Description", expenseEntity.description)
    }
}
