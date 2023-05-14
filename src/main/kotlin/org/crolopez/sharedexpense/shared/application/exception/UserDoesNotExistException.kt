package org.crolopez.sharedexpense.shared.application.exception

class UserDoesNotExistException(username: String) : Exception(
    "User $username does not exist"
)