package pl.coderslab.budgetmaster.expenses;

import lombok.*;
import pl.coderslab.budgetmaster.expenseCategory.ExpensesCategory;
import pl.coderslab.budgetmaster.users.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "expense_id")
    private Long id;

    @NotBlank (message = "Expense name cannot be empty")
    @Column(nullable = false)
    @Size(min = 2, max = 30, message = "Expense name must be between 2 and 30 characters")
    private String nameExpense;

    @NotNull(message = "The amount cannot be empty")
    @Column
    private BigDecimal amountOfExpense;

    @NotNull(message = "The date cannot be empty")
    @Column
    private LocalDate expenseDate;


    @ManyToOne (optional = false, fetch = FetchType.EAGER)
    private User user;


    @ManyToOne (optional = false, fetch = FetchType.EAGER)
    private ExpensesCategory expenseCategory;
}
