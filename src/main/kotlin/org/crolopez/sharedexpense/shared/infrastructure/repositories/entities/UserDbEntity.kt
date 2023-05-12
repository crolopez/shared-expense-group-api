package org.crolopez.sharedexpense.shared.infrastructure.repositories.entities

import io.micronaut.data.annotation.Join
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "group_user",
        joinColumns = [JoinColumn(name = "username")],
        inverseJoinColumns = [JoinColumn(name = "group_id")]
    )
    @Join("group_id")
    val groups: List<GroupDbEntity> = listOf()
}
