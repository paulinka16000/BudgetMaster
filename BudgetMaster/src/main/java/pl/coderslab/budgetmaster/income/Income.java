package pl.coderslab.budgetmaster.income;

import lombok.*;
import pl.coderslab.budgetmaster.users.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "incomes")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "income_id")
    private Long id;

    @Column(nullable = false)
    private String sourceOfIncome;
    @Column(nullable = false)
    private BigDecimal amountOfIncome;
    @Column(nullable = false)
    private LocalDate receiptDate;



    @ManyToOne(fetch = FetchType.EAGER)
    private User user;



}
