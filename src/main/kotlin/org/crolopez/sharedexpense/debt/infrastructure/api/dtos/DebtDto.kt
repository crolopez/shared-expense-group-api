package org.crolopez.sharedexpense.debt.infrastructure.api.dtos

data class DebtDto(
    val fromUser: String,
    val toUser: String,
    val amount: Double
)