package pl.coderslab.budgetmaster.users;

import lombok.*;
import pl.coderslab.budgetmaster.income.Income;

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
    private String email;

    @Column(nullable = false)
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "users")
    private List<Income> income = new ArrayList<>();


}


