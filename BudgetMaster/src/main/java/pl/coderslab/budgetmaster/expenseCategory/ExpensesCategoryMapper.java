package pl.coderslab.budgetmaster.expenseCategory;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public class ExpensesCategoryMapper {
    public ExpensesCategoryDTO mapToDTO(ExpensesCategory expensesCategory) {
        ExpensesCategoryDTO expensesCategoryDTO = new ExpensesCategoryDTO();
        expensesCategoryDTO.setId(expensesCategory.getId());
        expensesCategoryDTO.setName(expensesCategory.getName());
        return expensesCategoryDTO;
    }

    public ExpensesCategory mapToEntity(ExpensesCategoryDTO expensesCategoryDTO) {
        ExpensesCategory expensesCategory = new ExpensesCategory();
        expensesCategory.setId(expensesCategoryDTO.getId());
        expensesCategory.setName(expensesCategoryDTO.getName());
        return expensesCategory;
    }
}

