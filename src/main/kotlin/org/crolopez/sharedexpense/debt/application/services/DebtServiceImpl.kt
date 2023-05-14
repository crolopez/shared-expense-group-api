package org.crolopez.sharedexpense.debt.application.services

import jakarta.inject.Singleton
import org.crolopez.sharedexpense.balance.domain.entities.BalanceEntity
import org.crolopez.sharedexpense.debt.domain.entities.DebtEntity
import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import org.crolopez.sharedexpense.user.domain.entities.UserEntity
import java.lang.Math.abs

@Singleton
class DebtServiceImpl: DebtService {
    override fun getDebtsFromBalances(balances: List<BalanceEntity>): List<DebtEntity> {
        val usersWhoReceive = balances.filter{x -> x.amount > 0}.sortedByDescending { x -> x.amount }
        val usersWhoPay = balances.filter{x -> x.amount < 0}.sortedByDescending { x -> abs(x.amount) }
        val debts = mutableListOf<DebtEntity>()

        var i = 0
        var j = 0
        while (i < usersWhoReceive.size && j < usersWhoPay.size) {
            val fromUser = usersWhoPay[j].name
            val toUser = usersWhoReceive[i].name
            val amount = minOf(abs(usersWhoReceive[i].amount), abs(usersWhoPay[j].amount))

            debts.add(DebtEntity(
                fromUser = fromUser,
                toUser = toUser,
                amount = amount))

            usersWhoReceive[i].amount -= amount
            usersWhoPay[j].amount += amount

            if (usersWhoPay[j].amount == 0.0) {
                j++
            }

            if (usersWhoReceive[i].amount == 0.0) {
                i++
            }
        }

        return debts
    }
}