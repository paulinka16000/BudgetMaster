package pl.coderslab.budgetmaster.expenselimit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseLimitRepository extends JpaRepository<ExpenseLimit, Long> {
}
