package pl.coderslab.budgetmaster.users;

import lombok.*;
import pl.coderslab.budgetmaster.expenses.Expense;
import pl.coderslab.budgetmaster.income.Income;
import pl.coderslab.budgetmaster.savings.Savings;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank (message = "User nie może być pusty")
    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 20, message = "Imię użytkownika musi mieć od 2 do 20 znaków")
    private String userFirstName;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Income> incomesList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Expense> expenseArrayList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Savings> savingsList = new ArrayList<>();


}


