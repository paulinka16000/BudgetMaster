package pl.coderslab.budgetmaster.expenses;

import lombok.*;
import pl.coderslab.budgetmaster.expenseCategory.ExpensesCategory;
import pl.coderslab.budgetmaster.users.User;

import javax.persistence.*;
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

    @Column(nullable = false)
    private String nameExpense;

    @Column(nullable = false)
    private BigDecimal amountOfExpense;

    @Column(nullable = false)
    private LocalDate expenseDate;


    @ManyToOne (optional = false, fetch = FetchType.EAGER)
    private User user;

    @ManyToOne (optional = false, fetch = FetchType.EAGER)
    private ExpensesCategory expenseCategory;
}
