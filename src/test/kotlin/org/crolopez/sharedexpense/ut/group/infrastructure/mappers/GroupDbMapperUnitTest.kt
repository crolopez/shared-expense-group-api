package org.crolopez.sharedexpense.ut.group.infrastructure.mappers

import org.crolopez.sharedexpense.group.domain.entities.GroupEntity
import org.crolopez.sharedexpense.group.infrastructure.mappers.GroupDbMapper
import org.crolopez.sharedexpense.group.infrastructure.repositories.entities.GroupDbEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GroupDbMapperUnitTest {

    private lateinit var groupDbMapper: GroupDbMapper

    @BeforeEach
    fun setup() {
        groupDbMapper = GroupDbMapper()
    }

    @Test
    fun givenGroupDbEntity_whenConvert_thenReturnGroupEntityWithCorrectAttributes() {
        val groupDbEntity = GroupDbEntity(groupId = 1L, groupTitle = "Group 1")

        val groupEntity = groupDbMapper.convert(groupDbEntity)

        assertEquals(1L, groupEntity.groupId)
        assertEquals("Group 1", groupEntity.groupTitle)
    }
}
