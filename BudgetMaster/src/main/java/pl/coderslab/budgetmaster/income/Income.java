package pl.coderslab.budgetmaster.income;

import lombok.*;
import pl.coderslab.budgetmaster.users.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotBlank(message = "Revenue cannot be empty")
    @Column
    private String sourceOfIncome;
    @NotNull (message = "Revenue amount cannot be empty")
    @Column
    private BigDecimal amountOfIncome;

    @Column(nullable = false)
    private LocalDate receiptDate;



    @ManyToOne (optional = false, fetch = FetchType.EAGER)
    private User user;



}
