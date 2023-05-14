package org.crolopez.sharedexpense.shared.infrastructure.api.dtos

data class ResponseDto<T>(
    val data: List<DataDto<T>>? = null,
    val errors: List<ErrorDto>? = null,
)
