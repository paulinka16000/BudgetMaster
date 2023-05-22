package pl.coderslab.budgetmaster.expenseCategory;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")

public interface ExpensesCategoryMapper {
    ExpensesCategoryDTO toExpenseCategoryDTO(ExpensesCategory expenseCategory);
    ExpensesCategory toExpenseCategory(ExpensesCategoryDTO expenseCategoryDTO);
    void updateExpenseCategoryFromDTO(ExpensesCategoryDTO expenseCategoryDTO, @MappingTarget ExpensesCategory expenseCategory);
}


