package org.crolopez.sharedexpense.debt.infrastructure.mappers

import jakarta.inject.Singleton
import org.crolopez.sharedexpense.balance.domain.entities.BalanceEntity
import org.crolopez.sharedexpense.balance.infrastructure.api.dtos.BalanceDto
import org.crolopez.sharedexpense.debt.domain.entities.DebtEntity
import org.crolopez.sharedexpense.debt.infrastructure.api.dtos.DebtDto
import org.crolopez.sharedexpense.shared.infrastructure.api.dtos.DataDto
import org.crolopez.sharedexpense.shared.infrastructure.mappers.Mapper
import java.util.*

@Singleton
class DebtApiOutputMapper: Mapper<DebtEntity, DataDto<DebtDto>> {
    private val dataType: String = "debt"

    override fun convert(entity: DebtEntity): DataDto<DebtDto> {
        return DataDto(
            type = dataType,
            id = Random().nextInt(10000).toString(),
            attributes = DebtDto(
                amount = entity.amount,
                fromUser = entity.fromUser,
                toUser = entity.toUser
            )
        )
    }
}