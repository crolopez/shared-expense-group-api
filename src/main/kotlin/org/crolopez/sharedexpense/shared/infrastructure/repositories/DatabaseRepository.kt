package org.crolopez.sharedexpense.shared.infrastructure.repositories

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import org.crolopez.sharedexpense.shared.infrastructure.repositories.entities.GroupDbEntity

@Repository
interface DatabaseRepository: CrudRepository<GroupDbEntity, Long>{
}