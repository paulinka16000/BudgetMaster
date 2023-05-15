package pl.coderslab.budgetmaster.savings;

import lombok.*;
import pl.coderslab.budgetmaster.users.User;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "savings")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Savings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "saving_id")
    private Long id;
    @Column (nullable = false)
    private String savingsName;
    @Column (nullable = false)
    private BigDecimal savingsAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}

