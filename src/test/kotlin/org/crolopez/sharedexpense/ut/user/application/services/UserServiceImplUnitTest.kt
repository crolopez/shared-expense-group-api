package org.crolopez.sharedexpense.ut.user.application.services

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.crolopez.sharedexpense.user.application.repositories.UserRepository
import org.crolopez.sharedexpense.user.application.services.UserServiceImpl
import org.crolopez.sharedexpense.user.domain.entities.UserEntity
import org.mockito.InjectMocks
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class UserServiceImplUnitTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @InjectMocks
    private lateinit var userService: UserServiceImpl

    @Test
    fun givenUserName_whenGetUser_thenUserIsReturned() {
        val userName = "FakeUser1"
        val userEntity = UserEntity(
            name = "Fake User 1",
            userName = "FakeUser1",
            password = "fakePassword"
        )
        `when`(userRepository.getUser(userName)).thenReturn(Optional.of(userEntity))
        
        val result = userService.getUser(userName)

        assertEquals(userEntity, result.get())
    }

    @Test
    fun givenGroupId_whenGetUsersFromGroup_thenListOfUsersIsReturned() {
        val groupId: Long = 2835
        val userList = listOf(
            UserEntity(
                name = "Fake User 1",
                userName = "FakeUser1",
                password = "fakePassword1"
            ),
            UserEntity(
                name = "Fake User 2",
                userName = "FakeUser2",
                password = "fakePassword2"
            )
        )
        `when`(userRepository.getUsersFromGroup(groupId)).thenReturn(userList)

        val result = userService.getUsersFromGroup(groupId)

        assertEquals(userList, result)
    }

    @Test
    fun givenExistingUserName_whenUserExists_thenTrueIsReturned() {
        val userName = "FakeUser1"
        `when`(userRepository.userExists(userName)).thenReturn(true)

        val result = userService.userExists(userName)

        assertTrue(result)
    }

    @Test
    fun givenNonExistingUserName_whenUserExists_thenFalseIsReturned() {
        val userName = "NonExistingUser"
        `when`(userRepository.userExists(userName)).thenReturn(false)

        val result = userService.userExists(userName)

        assertFalse(result)
    }

    @Test
    fun givenExistingUserNameAndGroupId_whenUserExistsInGroup_thenTrueIsReturned() {
        val userName = "FakeUser1"
        val groupId: Long = 346672
        `when`(userRepository.userExistsInGroup(userName, groupId)).thenReturn(true)

        val result = userService.userExistsInGroup(userName, groupId)

        assertTrue(result)
    }

    @Test
    fun givenNonExistingUserNameAndExistingGroupId_whenUserExistsInGroup_thenFalseIsReturned() {
        val userName = "NonExistingUser"
        val groupId = 1L
        `when`(userRepository.userExistsInGroup(userName, groupId)).thenReturn(false)

        val result = userService.userExistsInGroup(userName, groupId)

        assertFalse(result)
    }
}