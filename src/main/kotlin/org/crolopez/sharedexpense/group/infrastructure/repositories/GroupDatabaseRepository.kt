package org.crolopez.sharedexpense.group.infrastructure.repositories

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import org.crolopez.sharedexpense.shared.infrastructure.repositories.entities.GroupDbEntity

@Repository
interface GroupDatabaseRepository: CrudRepository<GroupDbEntity, Long>{
}