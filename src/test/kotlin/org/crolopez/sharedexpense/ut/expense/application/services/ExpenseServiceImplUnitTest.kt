import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.crolopez.sharedexpense.expense.application.repositories.ExpenseRepository
import org.crolopez.sharedexpense.expense.application.services.ExpenseServiceImpl
import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import org.crolopez.sharedexpense.shared.application.exception.InvalidAmountException

@ExtendWith(MockitoExtension::class)
class ExpenseServiceImplUnitTest {

    @Mock
    private lateinit var expenseRepository: ExpenseRepository

    @InjectMocks
    private lateinit var expenseService: ExpenseServiceImpl

    @Test
    fun givenValidExpense_whenAddExpense_thenExpenseIsAdded() {
        val groupId: Long = 123
        val username = "FakeUser"
        val expenseEntity = ExpenseEntity(description = "Expense 1", amount = 50.0)

        expenseService.addExpense(groupId, username, expenseEntity)
    }

    @Test
    fun givenExpenseWithZeroAmount_whenAddExpense_thenInvalidAmountExceptionIsThrown() {
        val groupId: Long = 123
        val username = "FakeUser"
        val expenseEntity = ExpenseEntity(description = "Expense 1", amount = 0.0)

        assertThrows(InvalidAmountException::class.java) {
            expenseService.addExpense(groupId, username, expenseEntity)
        }
    }

    @Test
    fun givenExpenseWithNegativeAmount_whenAddExpense_thenInvalidAmountExceptionIsThrown() {
        val groupId: Long = 123
        val username = "FakeUser"
        val expenseEntity = ExpenseEntity(description = "Expense 1", amount = -50.0)

        assertThrows(InvalidAmountException::class.java) {
            expenseService.addExpense(groupId, username, expenseEntity)
        }
    }

    @Test
    fun givenGroupId_whenGetExpensesFromGroup_thenReturnExpenses() {
        val groupId: Long = 123
        val expenses = listOf(
            ExpenseEntity(description = "Expense 1", amount = 50.0),
            ExpenseEntity(description = "Expense 2", amount = 75.0)
        )
        `when`(expenseRepository.getExpensesFromGroup(groupId)).thenReturn(expenses)

        val result = expenseService.getExpensesFromGroup(groupId)

        assertEquals(expenses, result)
    }
}
