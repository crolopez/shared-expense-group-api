package org.crolopez.sharedexpense.ut.debt.infrastructure.mappers

import org.crolopez.sharedexpense.debt.domain.entities.DebtEntity
import org.crolopez.sharedexpense.debt.infrastructure.api.dtos.DebtDto
import org.crolopez.sharedexpense.debt.infrastructure.mappers.DebtApiOutputMapper
import org.crolopez.sharedexpense.shared.infrastructure.api.dtos.DataDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DebtApiOutputMapperUnitTest {

    private lateinit var debtApiOutputMapper: DebtApiOutputMapper

    @BeforeEach
    fun setup() {
        debtApiOutputMapper = DebtApiOutputMapper()
    }

    @Test
    fun givenDebtEntity_whenConvert_thenReturnDataDtoWithCorrectAttributes() {
        val debtEntity = DebtEntity(
            amount = 100.0,
            fromUser = "FakeUser1",
            toUser = "FakeUser2",
            currency = "EUR"
        )

        val dataDto = debtApiOutputMapper.convert(debtEntity)

        assertEquals("debt", dataDto.type)
        val id = dataDto.id.toInt()
        assertTrue(id in 0..9999)
        val debtDto = dataDto.attributes
        assertEquals(100.0, debtDto.amount)
        assertEquals("FakeUser1", debtDto.fromUser)
        assertEquals("FakeUser2", debtDto.toUser)
        assertEquals("EUR", debtDto.currency)
    }
}
