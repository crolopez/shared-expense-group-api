package org.crolopez.sharedexpense.ut.utils

import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import java.time.Instant

fun createExpenseEntity(amount: Double, username: String): ExpenseEntity {
    return ExpenseEntity(0, amount, "", Instant.MIN, "", username, "")
}
