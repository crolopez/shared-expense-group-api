package org.crolopez.sharedexpense.balance.domain.entities

data class BalanceEntity(
    var amount: Double,
    val name: String,
    var username: String,
    var currency: String
)