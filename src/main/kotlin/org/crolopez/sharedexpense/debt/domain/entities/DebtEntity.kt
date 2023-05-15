package org.crolopez.sharedexpense.debt.domain.entities

data class DebtEntity(
    val fromUser: String,
    val toUser: String,
    val amount: Double,
    val currency: String
)