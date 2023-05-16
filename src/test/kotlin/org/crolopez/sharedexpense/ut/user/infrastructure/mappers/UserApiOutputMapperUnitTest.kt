package org.crolopez.sharedexpense.ut.user.infrastructure.mappers

import org.crolopez.sharedexpense.shared.infrastructure.api.dtos.DataDto
import org.crolopez.sharedexpense.user.domain.entities.UserEntity
import org.crolopez.sharedexpense.user.infrastructure.api.dtos.UserDto
import org.crolopez.sharedexpense.user.infrastructure.mappers.UserApiOutputMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UserApiOutputMapperUnitTest {

    private lateinit var userApiOutputMapper: UserApiOutputMapper

    @BeforeEach
    fun setup() {
        userApiOutputMapper = UserApiOutputMapper()
    }

    @Test
    fun givenUserEntity_whenConvert_thenReturnDataDtoWithCorrectAttributes() {
        val userEntity = UserEntity(
            userName = "fake_user",
            name = "Fake User",
            password = "DummyPassword"
        )

        val dataDto = userApiOutputMapper.convert(userEntity)

        assertEquals("user", dataDto.type)
        assertEquals("fake_user", dataDto.id)
        val userDto = dataDto.attributes
        assertEquals("Fake User", userDto.name)
    }
}
