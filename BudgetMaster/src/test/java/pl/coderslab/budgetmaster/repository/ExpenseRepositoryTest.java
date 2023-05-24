package pl.coderslab.budgetmaster.repository;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.data.repository.query.Param;
import pl.coderslab.budgetmaster.expenseCategory.ExpensesCategory;
import pl.coderslab.budgetmaster.expenses.Expense;
import pl.coderslab.budgetmaster.expenses.ExpenseRepository;
import pl.coderslab.budgetmaster.users.User;


import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ExpenseRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ExpenseRepository expenseRepository;


    @Test
    public void testCalculateTotalExpensesByUserId() {
        // Given
        final Long userId = 1L;

        User user = new User();
        user.setId(userId);
        user.setUserFirstName("Paulina");
        entityManager.merge(user);

        final Long categoryId=1L;
        ExpensesCategory expensesCategory=new ExpensesCategory();
        expensesCategory.setId(categoryId);
        expensesCategory.setName("CREDITS");
        entityManager.merge(expensesCategory);

        Expense expense1 = new Expense();
        expense1.setUser(user);
        expense1.setAmountOfExpense(new BigDecimal("200.00"));
        expense1.setExpenseDate(LocalDate.now());
        expense1.setNameExpense("Shopping");
        expense1.setExpenseCategory(expensesCategory);
        entityManager.persist(expense1);

        Expense expense2 = new Expense();
        expense2.setUser(user);
        expense2.setAmountOfExpense(new BigDecimal("200.00"));
        expense2.setExpenseDate(LocalDate.now());
        expense2.setNameExpense("Shopping");
        expense2.setExpenseCategory(expensesCategory);
        entityManager.persist(expense2);

        entityManager.flush();

        // When
        BigDecimal totalExpenses = expenseRepository.calculateTotalExpensesByUserId(userId);

        // Then
        BigDecimal expectedTotalExpenses = new BigDecimal("400.00");
        assertEquals(expectedTotalExpenses, totalExpenses);
    }
}
