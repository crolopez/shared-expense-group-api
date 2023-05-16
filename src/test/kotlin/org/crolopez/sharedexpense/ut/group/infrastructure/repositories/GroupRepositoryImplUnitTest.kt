package org.crolopez.sharedexpense.ut.group.infrastructure.repositories

import jakarta.inject.Inject
import org.crolopez.sharedexpense.group.application.repositories.GroupRepository
import org.crolopez.sharedexpense.group.domain.entities.GroupEntity
import org.crolopez.sharedexpense.group.infrastructure.repositories.GroupDatabaseRepository
import org.crolopez.sharedexpense.group.infrastructure.repositories.GroupRepositoryImpl
import org.crolopez.sharedexpense.group.infrastructure.repositories.entities.GroupDbEntity
import org.crolopez.sharedexpense.shared.infrastructure.mappers.Mapper
import org.crolopez.sharedexpense.shared.infrastructure.repositories.relations.GroupUserRelationDatabaseRepository
import org.crolopez.sharedexpense.shared.infrastructure.repositories.relations.GroupUserRelationDbEntity
import org.crolopez.sharedexpense.shared.infrastructure.repositories.relations.GroupUserRelationId
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GroupRepositoryImplUnitTest {

    @Mock
    private lateinit var groupUserRepository: GroupUserRelationDatabaseRepository

    @Mock
    private lateinit var groupDatabaseRepository: GroupDatabaseRepository

    @Mock
    private lateinit var groupDbMapper: Mapper<GroupDbEntity, GroupEntity>

    @InjectMocks
    private lateinit var groupRepository: GroupRepositoryImpl

    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getGroupsFromUser_shouldReturnListOfGroups() {
        val username = "FakeUser1"
        val groupDbEntity = GroupDbEntity(groupId = 1L, groupTitle = "Group 1")
        val groupEntity = GroupEntity(groupId = 1L, groupTitle = "Group 1")
        val groupDbEntities = listOf(groupDbEntity)
        val groupEntities = listOf(groupEntity)

        `when`(groupDatabaseRepository.findByUsername(username)).thenReturn(groupDbEntities)
        `when`(groupDbMapper.convert(groupDbEntity)).thenReturn(groupEntity)

        val result = groupRepository.getGroupsFromUser(username)

        assertEquals(groupEntities, result)
    }

    @Test
    fun addUserToGroup_shouldSaveGroupUserRelation() {
        val groupId = 1L
        val username = "FakeUser2"

        groupRepository.addUserToGroup(groupId, username)

        val expectedRelation = GroupUserRelationDbEntity(
            id = GroupUserRelationId(groupId = groupId, username = username)
        )
        verify(groupRepository.groupUserRepository).save(expectedRelation)
    }
}
