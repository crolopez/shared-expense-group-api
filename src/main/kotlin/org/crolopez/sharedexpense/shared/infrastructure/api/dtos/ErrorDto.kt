package org.crolopez.sharedexpense.shared.infrastructure.api.dtos

data class ErrorDto(
    val code: ErrorCodeEnum,
    val title: String?,
    val source: SourceErrorDto? = null
)
