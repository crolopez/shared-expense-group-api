package org.crolopez.sharedexpense.ut.balance.application.services

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.crolopez.sharedexpense.balance.application.services.BalanceServiceImpl
import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import org.crolopez.sharedexpense.user.domain.entities.UserEntity
import java.time.Instant

class BalanceServiceImplUnitTest {

    private val balanceService = BalanceServiceImpl()

    @Test
    fun givenExpensesAndUsers_whenGetBalanceFromUsers_thenReturnListOfBalances() {
        val expenses = listOf(
            getExpenseEntity(50.0, "User1"),
            getExpenseEntity(30.0, "User2"),
            getExpenseEntity(20.0, "User1"),
            getExpenseEntity(40.0, "User3"),
        )
        val users = listOf(
            UserEntity("User1", "dummyPassword1", "FakeUser1"),
            UserEntity("User2", "dummyPassword2", "FakeUser2"),
            UserEntity("User3", "dummyPassword3", "FakeUser3")
        )

        val result = balanceService.getBalanceFromUsers(expenses, users)

        assertEquals(3, result.size)
        assertEquals("FakeUser1", result[0].name)
        assertEquals("User1", result[0].username)
        assertEquals(23.33, result[0].amount, 0.01)
        assertEquals("FakeUser2", result[1].name)
        assertEquals("User2", result[1].username)
        assertEquals(-16.66, result[1].amount, 0.01)
        assertEquals("FakeUser3", result[2].name)
        assertEquals("User3", result[2].username)
        assertEquals(-6.66, result[2].amount, 0.01)
    }

    private fun getExpenseEntity(amount: Double, username: String): ExpenseEntity {
        return ExpenseEntity(0,
            amount,
            "dummyCurrency",
            Instant.MIN,
            "dummyDescription",
            username,
            "dummyUser"
        )
    }
}
