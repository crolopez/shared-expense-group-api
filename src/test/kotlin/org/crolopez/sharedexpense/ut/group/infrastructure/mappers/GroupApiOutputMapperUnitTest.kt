package org.crolopez.sharedexpense.ut.group.infrastructure.mappers

import org.crolopez.sharedexpense.group.domain.entities.GroupEntity
import org.crolopez.sharedexpense.group.infrastructure.mappers.GroupApiOutputMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GroupApiOutputMapperUnitTest {

    private lateinit var groupApiOutputMapper: GroupApiOutputMapper

    @BeforeEach
    fun setup() {
        groupApiOutputMapper = GroupApiOutputMapper()
    }

    @Test
    fun givenGroupEntity_whenConvert_thenReturnDataDtoWithCorrectAttributes() {
        val groupEntity = GroupEntity(groupId = 1L, groupTitle = "Group 1")

        val dataDto = groupApiOutputMapper.convert(groupEntity)

        assertEquals("group", dataDto.type)
        assertEquals("1", dataDto.id)
        val groupDto = dataDto.attributes
        assertEquals("Group 1", groupDto.groupTitle)
    }
}
