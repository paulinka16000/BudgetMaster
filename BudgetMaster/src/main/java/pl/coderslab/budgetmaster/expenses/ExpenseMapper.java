package pl.coderslab.budgetmaster.expenses;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    ExpenseDTO toExpenseDTO(Expense expense);

    Expense toExpense(ExpenseDTO expenseDTO);

    @Mapping(target = "user", ignore = true)
    void updateExpenseFromDTO(ExpenseDTO expenseDTO, @MappingTarget Expense expense);
}