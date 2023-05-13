package org.crolopez.sharedexpense.shared.infrastructure.repositories.relations

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import org.crolopez.sharedexpense.shared.infrastructure.repositories.entities.GroupUserRelationDbEntity
import org.crolopez.sharedexpense.shared.infrastructure.repositories.entities.GroupUserRelationId

@Repository
interface GroupUserRelationDatabaseRepository: CrudRepository<GroupUserRelationDbEntity, GroupUserRelationId> {
}