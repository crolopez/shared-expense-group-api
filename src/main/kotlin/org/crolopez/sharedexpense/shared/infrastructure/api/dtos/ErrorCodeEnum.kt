package org.crolopez.sharedexpense.shared.infrastructure.api.dtos

enum class ErrorCodeEnum(code: Int) {
    UNEXPECTED(100),
    USER_NOT_IN_GROUP(101),
    USER_DOES_NOT_EXIST(102),
    USER_CANNOT_BE_ADDED(103),
    INVALID_AMOUNT(104)
}