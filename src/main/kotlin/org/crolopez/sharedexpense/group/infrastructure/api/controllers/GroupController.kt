package org.crolopez.sharedexpense.group.infrastructure.api.controllers

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.security.annotation.Secured
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.rules.SecurityRule
import jakarta.inject.Inject
import org.crolopez.sharedexpense.balance.domain.entities.BalanceEntity
import org.crolopez.sharedexpense.balance.infrastructure.api.dtos.BalanceDto
import org.crolopez.sharedexpense.debt.domain.entities.DebtEntity
import org.crolopez.sharedexpense.debt.infrastructure.api.dtos.DebtDto
import org.crolopez.sharedexpense.expense.infrastructure.api.dtos.ExpenseDto
import org.crolopez.sharedexpense.group.application.services.GroupService
import org.crolopez.sharedexpense.group.domain.entities.GroupEntity
import org.crolopez.sharedexpense.group.infrastructure.api.dtos.GroupDto
import org.crolopez.sharedexpense.shared.infrastructure.api.dtos.DataDto
import org.crolopez.sharedexpense.shared.infrastructure.api.dtos.ResponseDto
import org.crolopez.sharedexpense.shared.infrastructure.mappers.Mapper
import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import org.crolopez.sharedexpense.shared.application.exception.UserNotInGroupException
import org.crolopez.sharedexpense.user.domain.entities.UserEntity
import org.crolopez.sharedexpense.user.infrastructure.api.dtos.UserDto

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/v1/group")
class GroupController {

    val userKey = "sub"

    @Inject
    lateinit var groupService: GroupService

    @Inject
    lateinit var groupApiOutputMapper: Mapper<GroupEntity, DataDto<GroupDto>>

    @Inject
    lateinit var userApiOutputMapper: Mapper<UserEntity, DataDto<UserDto>>

    @Inject
    lateinit var expenseApiOutputMapper: Mapper<ExpenseEntity, DataDto<ExpenseDto>>

    @Inject
    lateinit var balanceApiOutputMapper: Mapper<BalanceEntity, DataDto<BalanceDto>>

    @Inject
    lateinit var debtApiOutputMapper: Mapper<DebtEntity, DataDto<DebtDto>>

    @Inject
    lateinit var expenseApiInputMapper: Mapper<ExpenseDto, ExpenseEntity>

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    fun getGroups(authentication: Authentication): ResponseDto<GroupDto> {
        val username: String = getUsername(authentication)
        val groups = groupService.getGroupsFromUser(username)
        return ResponseDto(
            data = groups.map { x -> groupApiOutputMapper.convert(x) })
    }

    @Get("/{groupId}/user")
    @Produces(MediaType.APPLICATION_JSON)
    fun getGroupUsers(groupId: Long, authentication: Authentication): ResponseDto<UserDto> {
        verifyValidGroup(groupId, authentication)
        val users = groupService.getUsersFromGroup(groupId)
        return ResponseDto(
            data = users.map { x -> userApiOutputMapper.convert(x) })
    }

    @Post("/{groupId}/user/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    fun addUserToGroup(groupId: Long, username: String, authentication: Authentication): ResponseDto<UserDto> {
        verifyValidGroup(groupId, authentication)
        groupService.addUserToGroup(groupId, username)
        val users = groupService.getUsersFromGroup(groupId)
        return ResponseDto(
            data = users.map { x -> userApiOutputMapper.convert(x) })
    }

    @Post("/{groupId}/expense")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun addExpenseToGroup(groupId: Long, @Body expenseDto: ExpenseDto, authentication: Authentication): ResponseDto<ExpenseDto> {
        verifyValidGroup(groupId, authentication)
        val username: String = authentication.attributes.get(userKey).toString()
        val expenseEntity = expenseApiInputMapper.convert(expenseDto)

        groupService.addExpenseToGroup(groupId, username, expenseEntity)
        val expenses = groupService.getExpensesFromGroup(groupId)

        return ResponseDto(
            data = expenses.map { x -> expenseApiOutputMapper.convert(x) })
    }

    @Get("/{groupId}/expense")
    @Produces(MediaType.APPLICATION_JSON)
    fun getGroupExpenses(groupId: Long, authentication: Authentication): ResponseDto<ExpenseDto> {
        verifyValidGroup(groupId, authentication)
        val expenses = groupService.getExpensesFromGroup(groupId)
        return ResponseDto(
            data = expenses.map { x -> expenseApiOutputMapper.convert(x) })
    }

    @Get("/{groupId}/balance")
    @Produces(MediaType.APPLICATION_JSON)
    fun getGroupBalance(groupId: Long, authentication: Authentication): ResponseDto<BalanceDto> {
        verifyValidGroup(groupId, authentication)
        val balance = groupService.getBalanceFromGroup(groupId)
        return ResponseDto(
            data = balance.map { x -> balanceApiOutputMapper.convert(x) })
    }

    @Get("/{groupId}/debt")
    @Produces(MediaType.APPLICATION_JSON)
    fun getGroupDebts(groupId: Long, authentication: Authentication): ResponseDto<DebtDto> {
        verifyValidGroup(groupId, authentication)
        val debts = groupService.getDebtsFromGroup(groupId)
        return ResponseDto(
            data = debts.map { x -> debtApiOutputMapper.convert(x) })
    }

    private fun verifyValidGroup(groupId: Long, authentication: Authentication) {
        val username = getUsername(authentication)
        if (groupService.userIsInGroup(groupId, username) == false) {
            throw UserNotInGroupException(groupId, username)
        }
    }

    private fun getUsername(authentication: Authentication): String {
        return authentication.attributes.get(userKey).toString()
    }

}