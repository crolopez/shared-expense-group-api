package org.crolopez.sharedexpense.expense.application.services

import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.crolopez.sharedexpense.expense.application.repositories.ExpenseRepository
import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import org.crolopez.sharedexpense.shared.application.exception.InvalidAmountException
import org.crolopez.sharedexpense.user.application.repositories.UserRepository
import org.crolopez.sharedexpense.user.application.services.UserService
import org.crolopez.sharedexpense.user.domain.entities.UserEntity
import java.util.*

@Singleton
class ExpenseServiceImpl: ExpenseService {
    @Inject
    lateinit var expenseRepository: ExpenseRepository

    override fun addExpense(groupId: Long, username: String, expenseEntity: ExpenseEntity) {
        validateAmount(expenseEntity)
        expenseRepository.addExpense(groupId, username, expenseEntity)
    }

    override fun getExpensesFromGroup(groupId: Long): List<ExpenseEntity> {
        return expenseRepository.getExpensesFromGroup(groupId)
    }

    private fun validateAmount(expenseEntity: ExpenseEntity) {
        if (expenseEntity.amount <= 0.0) {
            throw InvalidAmountException(expenseEntity.amount)
        }
    }
}