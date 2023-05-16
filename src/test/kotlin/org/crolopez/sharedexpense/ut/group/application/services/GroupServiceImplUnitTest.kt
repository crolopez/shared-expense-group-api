package org.crolopez.sharedexpense.ut.group.application.services

import org.crolopez.sharedexpense.balance.application.services.BalanceService
import org.crolopez.sharedexpense.debt.application.services.DebtService
import org.crolopez.sharedexpense.expense.application.services.ExpenseService
import org.crolopez.sharedexpense.group.application.repositories.GroupRepository
import org.crolopez.sharedexpense.group.application.services.GroupServiceImpl
import org.crolopez.sharedexpense.group.domain.entities.GroupEntity
import org.crolopez.sharedexpense.shared.application.exception.UserDoesNotExistException
import org.crolopez.sharedexpense.user.application.services.UserService
import org.crolopez.sharedexpense.user.domain.entities.UserEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class GroupServiceImplUnitTest {

    @Mock
    private lateinit var groupRepository: GroupRepository

    @Mock
    private lateinit var userService: UserService

    @Mock
    private lateinit var expenseService: ExpenseService

    @Mock
    private lateinit var balanceService: BalanceService

    @Mock
    private lateinit var debtService: DebtService

    @InjectMocks
    private lateinit var groupService: GroupServiceImpl

    @Test
    fun givenUsername_whenGetGroupsFromUser_thenReturnListOfGroups() {
        val username = "FakeUser1"
        val groupId1: Long = 1
        val groupId2: Long = 2
        val groups = listOf(
            GroupEntity("Group 1", groupId1),
            GroupEntity("Group 2", groupId2)
        )
        `when`(groupRepository.getGroupsFromUser(username)).thenReturn(groups)

        val result = groupService.getGroupsFromUser(username)

        assertEquals(groups, result)
    }

    @Test
    fun givenGroupId_whenGetUsersFromGroup_thenReturnListOfUsers() {
        val groupId: Long = 1
        val userList = listOf(
            UserEntity("FakeUser1", "password1","User1"),
            UserEntity("FakeUser2", "password2", "User2")
        )
        `when`(userService.getUsersFromGroup(groupId)).thenReturn(userList)

        val result = groupService.getUsersFromGroup(groupId)

        assertEquals(userList, result)
    }

    @Test
    fun givenValidGroupIdAndUsername_whenAddUserToGroup_thenUserAddedSuccessfully() {
        val groupId: Long = 1
        val username = "FakeUser1"
        `when`(userService.userExists(username)).thenReturn(true)
        `when`(userService.userExistsInGroup(username, groupId)).thenReturn(false)

        groupService.addUserToGroup(groupId, username)

        verify(groupRepository).addUserToGroup(groupId, username)
    }

    @Test
    fun givenInvalidUsername_whenAddUserToGroup_thenThrowUserDoesNotExistException() {
        val groupId: Long = 1
        val username = "NonExistingUser"
        `when`(userService.userExists(username)).thenReturn(false)

        assertThrows(UserDoesNotExistException::class.java) {
            groupService.addUserToGroup(groupId, username)
        }
    }
}
