package org.crolopez.sharedexpense.group.application.services

import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.crolopez.sharedexpense.expense.application.services.ExpenseService
import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import org.crolopez.sharedexpense.group.application.repositories.GroupRepository
import org.crolopez.sharedexpense.group.domain.entities.GroupEntity
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

    override fun getGroupsFromUser(username: String): List<GroupEntity> {
        return groupRepository.getGroupsFromUser(username)
    }

    override fun getUsersFromGroup(groupId: Long): List<UserEntity> {
        return userService.getUsersFromGroup(groupId)
    }

    override fun addUserToGroup(groupId: Long, username: String) {
        groupRepository.addUserToGroup(groupId, username)
    }

    override fun addExpenseToGroup(groupId: Long, username: String, expenseEntity: ExpenseEntity) {
        expenseService.addExpense(groupId, username, expenseEntity)
    }

    override fun getExpensesFromGroup(groupId: Long): List<ExpenseEntity> {
        return expenseService.getExpensesFromGroup(groupId)
    }
}