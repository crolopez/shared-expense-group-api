package org.crolopez.sharedexpense.user.infrastructure.repositories

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import org.crolopez.sharedexpense.shared.infrastructure.repositories.entities.UserDbEntity

@Repository
interface UserDatabaseRepository: CrudRepository<UserDbEntity, String>{
}