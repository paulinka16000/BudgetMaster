package pl.coderslab.budgetmaster.income;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Income Category id", example = "1")
    private Long id;
    @Schema(description = "Source of income", example = "salary")
    private String sourceOfIncome;
    @Schema(description = "Amount of income", example = "3000.00")
    private BigDecimal amountOfIncome;
    @Schema(description = "Date of receipt", example = "2022-05-01")
    private LocalDate receiptDate;
    @Schema(description = "Income user id", example = "1")
    private Long userId;

    }







