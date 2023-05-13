package org.crolopez.sharedexpense.debt.domain.entities

data class DebtEntity(
    val amount: Double,
    val name: String,
    var username: String
)