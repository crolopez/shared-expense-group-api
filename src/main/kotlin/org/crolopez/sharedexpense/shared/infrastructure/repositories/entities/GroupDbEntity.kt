package org.crolopez.sharedexpense.shared.infrastructure.repositories.entities

import javax.persistence.*


@Entity
@Table(name = "EXPENSES_GROUP")
data class GroupDbEntity (
    @Id
    @Column(name = "group_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val groupId: Long = 0,

    @Column(name = "group_title", nullable = false)
    val groupTitle: String = ""
)
