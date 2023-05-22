package pl.coderslab.budgetmaster.expenseCategory;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ExpensesCategoryDTO {

    private Long id;
    private String name;
}
