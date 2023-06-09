package org.crolopez.sharedexpense.group.application.services

import org.crolopez.sharedexpense.balance.domain.entities.BalanceEntity
import org.crolopez.sharedexpense.debt.domain.entities.DebtEntity
import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import org.crolopez.sharedexpense.group.domain.entities.GroupEntity
import org.crolopez.sharedexpense.user.domain.entities.UserEntity

interface GroupService {
    fun getGroupsFromUser(username: String): List<GroupEntity>
    fun getUsersFromGroup(groupId: Long): List<UserEntity>
    fun addUserToGroup(groupId: Long, username: String)
    fun addExpenseToGroup(groupId: Long, username: String, expenseEntity: ExpenseEntity)
    fun getExpensesFromGroup(groupId: Long): List<ExpenseEntity>
    fun getBalanceFromGroup(groupId: Long): List<BalanceEntity>
    fun getDebtsFromGroup(groupId: Long): List<DebtEntity>
    fun userIsInGroup(groupId: Long, username: String): Any
}