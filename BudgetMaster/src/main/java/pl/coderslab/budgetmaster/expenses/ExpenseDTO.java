package pl.coderslab.budgetmaster.expenses;

import lombok.*;
import pl.coderslab.budgetmaster.expenseCategory.ExpensesCategory;
import pl.coderslab.budgetmaster.users.User;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ExpenseDTO {
    private Long id;
    private String nameExpense;
    private BigDecimal amountOfExpense;
    private LocalDate expenseDate;
    private Long categoriesId;
    private Long userId;


}
