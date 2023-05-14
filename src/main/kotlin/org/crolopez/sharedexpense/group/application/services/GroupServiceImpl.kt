package org.crolopez.sharedexpense.group.application.services

import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.crolopez.sharedexpense.balance.application.services.BalanceService
import org.crolopez.sharedexpense.balance.domain.entities.BalanceEntity
import org.crolopez.sharedexpense.debt.application.services.DebtService
import org.crolopez.sharedexpense.debt.domain.entities.DebtEntity
import org.crolopez.sharedexpense.expense.application.services.ExpenseService
import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import org.crolopez.sharedexpense.group.application.repositories.GroupRepository
import org.crolopez.sharedexpense.group.domain.entities.GroupEntity
import org.crolopez.sharedexpense.shared.application.exception.UserCannotBeAddedException
import org.crolopez.sharedexpense.shared.application.exception.UserDoesNotExistException
import org.crolopez.sharedexpense.user.application.services.UserService
import org.crolopez.sharedexpense.user.domain.entities.UserEntity

@Singleton
class GroupServiceImpl: GroupService {
    @Inject
    lateinit var groupRepository: GroupRepository

    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var expenseService: ExpenseService

    @Inject
    lateinit var balanceService: BalanceService

    @Inject
    lateinit var debtService: DebtService

    override fun getGroupsFromUser(username: String): List<GroupEntity> {
        return groupRepository.getGroupsFromUser(username)
    }

    override fun getUsersFromGroup(groupId: Long): List<UserEntity> {
        return userService.getUsersFromGroup(groupId)
    }

    override fun addUserToGroup(groupId: Long, username: String) {
        verifyUserExits(username)
        verifyUserCanBeAdded(groupId, username)
        groupRepository.addUserToGroup(groupId, username)
    }

    override fun addExpenseToGroup(groupId: Long, username: String, expenseEntity: ExpenseEntity) {
        expenseService.addExpense(groupId, username, expenseEntity)
    }

    override fun getExpensesFromGroup(groupId: Long): List<ExpenseEntity> {
        return expenseService.getExpensesFromGroup(groupId)
    }

    override fun getBalanceFromGroup(groupId: Long): List<BalanceEntity> {
        val users = getUsersFromGroup(groupId)
        val expenses = getExpensesFromGroup(groupId)
        return balanceService.getBalanceFromUsers(expenses, users)
    }

    override fun getDebtsFromGroup(groupId: Long): List<DebtEntity> {
        val balances = getBalanceFromGroup(groupId)
        return debtService.getDebtsFromBalances(balances)
    }

    override fun userIsInGroup(groupId: Long, username: String): Any {
        return getGroupsFromUser(username).filter { x -> x.groupId == groupId }.isNotEmpty()
    }

    private fun verifyUserExits(username: String) {
        if (!userService.userExists(username)) {
            throw UserDoesNotExistException(username)
        }
    }

    private fun verifyUserCanBeAdded(groupId: Long, username: String) {
        if (userService.userExistsInGroup(username, groupId)) {
            throw UserCannotBeAddedException(groupId, username)
        }
    }
}