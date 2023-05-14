package org.crolopez.sharedexpense.shared.application.exception

class UserNotInGroupException(groupId: Long, username: String) : Exception(
    "User $username is not in $groupId group"
)