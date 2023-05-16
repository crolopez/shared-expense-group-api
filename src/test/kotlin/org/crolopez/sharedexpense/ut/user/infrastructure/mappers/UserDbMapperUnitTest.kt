package org.crolopez.sharedexpense.ut.user.infrastructure.mappers

import org.crolopez.sharedexpense.user.infrastructure.mappers.UserDbMapper
import org.crolopez.sharedexpense.user.infrastructure.repositories.entities.UserDbEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UserDbMapperUnitTest {

    private lateinit var userDbMapper: UserDbMapper

    @BeforeEach
    fun setup() {
        userDbMapper = UserDbMapper()
    }

    @Test
    fun givenUserDbEntity_whenConvert_thenReturnUserEntityWithCorrectAttributes() {
        val userDbEntity = UserDbEntity(
            username = "fake_user",
            password = "password123",
            name = "Fake User"
        )

        val userEntity = userDbMapper.convert(userDbEntity)

        assertEquals("fake_user", userEntity.userName)
        assertEquals("password123", userEntity.password)
        assertEquals("Fake User", userEntity.name)
    }
}
