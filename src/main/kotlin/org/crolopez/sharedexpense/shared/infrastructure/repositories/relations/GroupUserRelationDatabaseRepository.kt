package org.crolopez.sharedexpense.shared.infrastructure.repositories.relations

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface GroupUserRelationDatabaseRepository: CrudRepository<GroupUserRelationDbEntity, GroupUserRelationId> {
}