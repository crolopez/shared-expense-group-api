package org.crolopez.sharedexpense.group.infrastructure.api.controllers

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.security.annotation.Secured
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.rules.SecurityRule
import jakarta.inject.Inject
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
    lateinit var debtsApiOutputMapper: Mapper<DebtEntity, DataDto<DebtDto>>

    @Inject
    lateinit var expenseApiInputMapper: Mapper<ExpenseDto, ExpenseEntity>

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    fun getGroups(authentication: Authentication): ResponseDto<GroupDto> {
        val username: String = authentication.attributes.get(userKey).toString()
        val groups = groupService.getGroupsFromUser(username)
        return ResponseDto(
            data = groups.map { x -> groupApiOutputMapper.convert(x) })
    }

    @Get("/{groupId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getGroupUsers(groupId: Long, authentication: Authentication): ResponseDto<UserDto> {
        //val username: String = authentication.attributes.get(userKey).toString()
        // TODO: ADD VALIDATION FOR USER GROUP ~~~~
        val users = groupService.getUsersFromGroup(groupId)
        return ResponseDto(
            data = users.map { x -> userApiOutputMapper.convert(x) })
    }

    @Post("/{groupId}/user/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    fun addUserToGroup(groupId: Long, username: String, authentication: Authentication): ResponseDto<UserDto> {
        //val username: String = authentication.attributes.get(userKey).toString()
        // TODO: ADD VALIDATION FOR USER GROUP ~~~~
        groupService.addUserToGroup(groupId, username)
        val users = groupService.getUsersFromGroup(groupId)
        return ResponseDto(
            data = users.map { x -> userApiOutputMapper.convert(x) })
    }

    @Post("/{groupId}/expense")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun addExpenseToGroup(groupId: Long, @Body expenseDto: ExpenseDto, authentication: Authentication): ResponseDto<ExpenseDto> {
        // TODO: ADD VALIDATION FOR USER GROUP ~~~~
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
        // TODO: ADD VALIDATION FOR USER GROUP ~~~~
        val expenses = groupService.getExpensesFromGroup(groupId)
        return ResponseDto(
            data = expenses.map { x -> expenseApiOutputMapper.convert(x) })
    }

    @Get("/{groupId}/debts")
    @Produces(MediaType.APPLICATION_JSON)
    fun getGroupDebts(groupId: Long, authentication: Authentication): ResponseDto<DebtDto> {
        // TODO: ADD VALIDATION FOR USER GROUP ~~~~
        val debts = groupService.getDebtsFromGroup(groupId)
        return ResponseDto(
            data = debts.map { x -> debtsApiOutputMapper.convert(x) })
    }

}