package org.crolopez.sharedexpense.shared.infrastructure.mappers

interface Mapper<EntityA, EntityB> {
    fun convert(entityA: EntityA): EntityB
}