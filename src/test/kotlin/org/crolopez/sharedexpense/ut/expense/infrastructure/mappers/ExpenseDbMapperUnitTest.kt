package org.crolopez.sharedexpense.ut.expense.infrastructure.mappers

import org.crolopez.sharedexpense.expense.infrastructure.mappers.ExpenseDbMapper
import org.crolopez.sharedexpense.group.infrastructure.repositories.entities.ExpenseDbEntity
import org.crolopez.sharedexpense.user.infrastructure.repositories.entities.UserDbEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Instant

class ExpenseDbMapperUnitTest {

    private lateinit var expenseDbMapper: ExpenseDbMapper

    @BeforeEach
    fun setup() {
        expenseDbMapper = ExpenseDbMapper()
    }

    @Test
    fun givenExpenseDbEntity_whenConvert_thenReturnExpenseEntityWithCorrectAttributes() {
        val expenseDbEntity = ExpenseDbEntity(
            id = 1,
            amount = 100.0,
            currency = "USD",
            dateCreated = Instant.now().epochSecond,
            description = "Expense Description",
            user = UserDbEntity(username = "FakeUser1", name = "fakeUserName")
        )

        val expenseEntity = expenseDbMapper.convert(expenseDbEntity)

        assertEquals(1L, expenseEntity.id)
        assertEquals(100.0, expenseEntity.amount)
        assertEquals("USD", expenseEntity.currency)
        assertEquals(expenseDbEntity.dateCreated, expenseEntity.dateCreated.epochSecond)
        assertEquals("Expense Description", expenseEntity.description)
        assertEquals("FakeUser1", expenseEntity.username)
        assertEquals("fakeUserName", expenseEntity.user)
    }
}
