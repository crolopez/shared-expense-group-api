package org.crolopez.sharedexpense.group.infrastructure.api.controllers

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import jakarta.inject.Inject
import org.crolopez.sharedexpense.group.application.services.GroupService
import org.crolopez.sharedexpense.group.domain.entities.GroupEntity
import org.crolopez.sharedexpense.group.infrastructure.api.dtos.GroupDto
import org.crolopez.sharedexpense.shared.infrastructure.api.dtos.DataDto
import org.crolopez.sharedexpense.shared.infrastructure.api.dtos.ResponseDto
import org.crolopez.sharedexpense.shared.infrastructure.mappers.Mapper

@Controller("/v1/group")
class GroupController {

    @Inject
    lateinit var groupService: GroupService

    @Inject
    lateinit var groupApiMapper: Mapper<GroupEntity, DataDto<GroupDto>>

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    fun getGroups(): ResponseDto<GroupDto> {
        val groups = groupService.getGroupsFromUser("dummyUser")
        return ResponseDto(
            data = groups.map { x -> groupApiMapper.convert(x) })
    }
}