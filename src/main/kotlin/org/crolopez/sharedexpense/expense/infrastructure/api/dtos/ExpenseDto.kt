package org.crolopez.sharedexpense.expense.infrastructure.api.dtos

import java.time.Instant
import java.util.Date
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class ExpenseDto(
    @field:NotNull
    @field:Positive
    val amount: Double,

    @field:NotNull
    val description: String,

    val user: String,

    val dateCreated: String? = null,

    val currency: String? = null
)