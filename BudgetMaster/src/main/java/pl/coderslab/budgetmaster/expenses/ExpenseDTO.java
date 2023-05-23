package pl.coderslab.budgetmaster.expenses;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Expenses id", example = "1")
    private Long id;
    @Schema(description = "Expense name", example ="mortgage")
    private String nameExpense;
    @Schema(description = "Expense amount", example ="2222.74")
    private BigDecimal amountOfExpense;
    @Schema(description = "Expense date", example ="2023-05-05")
    private LocalDate expenseDate;
    @Schema(description = "Expense category id", example ="5")
    private Long categoriesId;
    @Schema(description = "Expense user id", example ="2")
    private Long userId;




}
