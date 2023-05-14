package org.crolopez.sharedexpense.balance.domain.entities

data class BalanceEntity(
    val amount: Double,
    val name: String,
    var username: String
)