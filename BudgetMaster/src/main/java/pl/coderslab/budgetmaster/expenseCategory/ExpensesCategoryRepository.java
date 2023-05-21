package pl.coderslab.budgetmaster.expenseCategory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesCategoryRepository extends JpaRepository<ExpensesCategory, Long> {
}
