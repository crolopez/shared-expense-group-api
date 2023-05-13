package org.crolopez.sharedexpense.expense.domain.entities

import java.time.Instant

data class ExpenseEntity(
    val id: Long,
    val amount: Double,
    val currency: String,
    var dateCreated: Instant,
    val description: String,
    val username: String,
    val user: String
) {
    constructor(amount: Double, description: String) : this(
        id = 0,
        amount= amount,
        currency = "",
        dateCreated = Instant.MIN,
        description = description,
        username = "",
        user = "")
}