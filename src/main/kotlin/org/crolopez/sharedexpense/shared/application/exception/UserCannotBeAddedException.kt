package org.crolopez.sharedexpense.shared.application.exception

class UserCannotBeAddedException(groupId: Long, username: String) : Exception(
    "User $username cannot be added to $groupId group"
)