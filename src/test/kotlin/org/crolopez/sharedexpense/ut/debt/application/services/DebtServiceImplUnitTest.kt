package org.crolopez.sharedexpense.ut.debt.application.services

import org.crolopez.sharedexpense.balance.domain.entities.BalanceEntity
import org.crolopez.sharedexpense.debt.application.services.DebtServiceImpl
import org.crolopez.sharedexpense.debt.domain.entities.DebtEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DebtServiceImplUnitTest {

    private val debtService = DebtServiceImpl()

    @Test
    fun givenBalances_whenGetDebtsFromBalances_thenShouldCalculateDebtsCorrectly() {
        val balances = listOf(
            BalanceEntity(15.0, "FakeUser1", "fakeuser_1", "dummyCurrency"),
            BalanceEntity(-5.0, "FakeUser2", "fakeuser_2", "dummyCurrency"),
            BalanceEntity(-10.0, "FakeUser3", "fakeuser3", "dummyCurrency")
        )

        val debts = debtService.getDebtsFromBalances(balances)

        assertEquals(2, debts.size)
        assertEquals(debts[0], DebtEntity("FakeUser3", "FakeUser1", 10.0, "EUR"))
        assertEquals(debts[1], DebtEntity("FakeUser2", "FakeUser1", 5.0, "EUR"))
    }
}
