package pl.coderslab.budgetmaster.expenseCategory;

import lombok.*;
import pl.coderslab.budgetmaster.expenses.Expense;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "expenses_categories", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpensesCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "expenses_categories_id")
    private Long id;


    @NotBlank (message = "Expense category cannot be empty")
    @Column(unique = true, nullable = false)
    private String name;


    @OneToMany(mappedBy = "expenseCategory", cascade = CascadeType.ALL)
    private List<Expense> expenseList= new ArrayList<>();
}
