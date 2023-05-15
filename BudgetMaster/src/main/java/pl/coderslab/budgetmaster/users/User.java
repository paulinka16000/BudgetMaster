package pl.coderslab.budgetmaster.users;

import lombok.*;
import pl.coderslab.budgetmaster.expenses.Expense;
import pl.coderslab.budgetmaster.income.Income;
import pl.coderslab.budgetmaster.savings.Savings;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String userFirstName;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Income> incomesList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Expense> expenseArrayList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Savings> savingsList = new ArrayList<>();


}


