package org.crolopez.sharedexpense.shared.application.exception

class InvalidAmountException(amount: Double) : Exception(
    "Amount $amount is not valid. Must be greater than 0"
)