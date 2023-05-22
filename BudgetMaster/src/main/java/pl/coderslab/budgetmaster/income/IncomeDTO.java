package pl.coderslab.budgetmaster.income;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pl.coderslab.budgetmaster.users.User;
import pl.coderslab.budgetmaster.users.UserDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class IncomeDTO {

    private Long id;
    private String sourceOfIncome;
    private BigDecimal amountOfIncome;
    private LocalDate receiptDate;
    private Long userId;

    }







