package org.crolopez.sharedexpense.shared.infrastructure.repositories.entities

import javax.persistence.*

@Entity
@Table(name = "USER_DATA")
class UserDbEntity {
    @Id
    @Column(name = "username", nullable = false)
    val username: String = ""

    @Column(name = "password", nullable = false)
    val password: String = ""

    @Column(name = "name", nullable = false)
    val name: String = ""

    @ManyToMany(mappedBy = "users")
    val groups: List<GroupDbEntity> = listOf()
}
