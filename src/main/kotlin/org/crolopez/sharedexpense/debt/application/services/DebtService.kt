package org.crolopez.sharedexpense.debt.application.services

import org.crolopez.sharedexpense.balance.domain.entities.BalanceEntity
import org.crolopez.sharedexpense.debt.domain.entities.DebtEntity
import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import org.crolopez.sharedexpense.user.domain.entities.UserEntity

interface DebtService {
    fun getDebtsFromBalances(balances: List<BalanceEntity>): List<DebtEntity>
}