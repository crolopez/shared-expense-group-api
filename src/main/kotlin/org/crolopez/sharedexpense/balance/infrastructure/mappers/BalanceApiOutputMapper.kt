package org.crolopez.sharedexpense.balance.infrastructure.mappers

import jakarta.inject.Singleton
import org.crolopez.sharedexpense.balance.domain.entities.BalanceEntity
import org.crolopez.sharedexpense.balance.infrastructure.api.dtos.BalanceDto
import org.crolopez.sharedexpense.shared.infrastructure.api.dtos.DataDto
import org.crolopez.sharedexpense.shared.infrastructure.mappers.Mapper

@Singleton
class BalanceApiOutputMapper: Mapper<BalanceEntity, DataDto<BalanceDto>> {
    private val dataType: String = "balance"

    override fun convert(entity: BalanceEntity): DataDto<BalanceDto> {
        return DataDto(
            type = dataType,
            id = entity.username,
            attributes = BalanceDto(
                amount = entity.amount,
                name = entity.name
            )
        )
    }
}