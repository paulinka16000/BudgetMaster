package pl.coderslab.budgetmaster.expenseCategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ExpensesCategoryDTO {
    @Schema(description = "Expenses Category id", example = "1")
    private Long id;

    @Schema(description = "Expenses Category name", example = "CREDITS")
    private String name;
}
