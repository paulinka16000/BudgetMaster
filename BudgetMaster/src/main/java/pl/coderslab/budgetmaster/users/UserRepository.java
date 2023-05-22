package pl.coderslab.budgetmaster.users;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.budgetmaster.expenses.Expense;
import pl.coderslab.budgetmaster.income.Income;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

}
