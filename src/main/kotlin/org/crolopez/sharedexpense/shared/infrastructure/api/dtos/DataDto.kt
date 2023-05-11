package org.crolopez.sharedexpense.shared.infrastructure.api.dtos

data class DataDto<T>(
    val type: String,
    val id: String,
    val attributes: T
)
