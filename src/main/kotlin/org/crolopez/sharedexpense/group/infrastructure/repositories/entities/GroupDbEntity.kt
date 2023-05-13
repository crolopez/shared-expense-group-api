package org.crolopez.sharedexpense.group.infrastructure.repositories.entities

import io.micronaut.data.annotation.Join
import org.crolopez.sharedexpense.user.infrastructure.repositories.entities.UserDbEntity
import javax.persistence.*

@Entity
@Table(name = "EXPENSES_GROUP")
data class GroupDbEntity (
    @Id
    @Column(name = "group_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val groupId: Long = 0,

    @Column(name = "group_title", nullable = false)
    val groupTitle: String = "",

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "group_user",
        joinColumns = [JoinColumn(name = "group_id")],
        inverseJoinColumns = [JoinColumn(name = "username")]
    )
    @Join("username")
    val users: MutableSet<UserDbEntity> = HashSet()
)