package org.crolopez.sharedexpense.shared.infrastructure.repositories.relations

import java.io.Serializable
import javax.persistence.*

@Embeddable
data class GroupUserRelationId(
    @Column(name = "group_id", nullable = false)
    val groupId: Long = 0,

    @Column(name = "username", nullable = false)
    val username: String = "",
) : Serializable

@Entity
@Table(name = "GROUP_USER", uniqueConstraints = [UniqueConstraint(columnNames = ["group_id", "username"])])
data class GroupUserRelationDbEntity (
    @EmbeddedId
    val id: GroupUserRelationId,
)