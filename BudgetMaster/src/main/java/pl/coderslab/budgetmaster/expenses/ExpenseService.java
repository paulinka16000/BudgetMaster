package pl.coderslab.budgetmaster.expenses;

import org.springframework.stereotype.Service;
import pl.coderslab.budgetmaster.users.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<ExpenseDTO> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        return mapExpensesToDTOs(expenses);
    }

    public ExpenseDTO getExpenseByUserId(Long user_id) {
        Optional<Expense> expenseOptional = expenseRepository.findById(user_id);
        return expenseOptional.map(this::mapExpenseToDTO).orElse(null);
    }


    public ExpenseDTO createExpense(ExpenseDTO expenseDTO) {
        Expense expense = mapDTOToExpense(expenseDTO);
        Expense createdExpense = expenseRepository.save(expense);
        return mapExpenseToDTO(createdExpense);
    }

    public ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO) {
        Optional<Expense> expenseOptional = expenseRepository.findById(id);
        if (expenseOptional.isPresent()) {
            Expense expense = expenseOptional.get();
            expense.setAmountOfExpense(expenseDTO.getAmountOfExpense());

            expense.setNameExpense(expenseDTO.getNameExpense());
            expense.setExpenseDate(expenseDTO.getExpenseDate());

            Expense updatedExpense = expenseRepository.save(expense);
            return mapExpenseToDTO(updatedExpense);
        }
        return null;
    }

    public boolean deleteExpense(Long id) {
        if (expenseRepository.existsById(id)) {
            expenseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private List<ExpenseDTO> mapExpensesToDTOs(List<Expense> expenses) {
        return expenses.stream()
                .map(this::mapExpenseToDTO)
                .collect(Collectors.toList());
    }

    private ExpenseDTO mapExpenseToDTO(Expense expense) {
        ExpenseDTO expenseDTO = new ExpenseDTO();
        expenseDTO.setId(expense.getId());
        expenseDTO.setAmountOfExpense(expense.getAmountOfExpense());

        expenseDTO.setExpenseDate(expense.getExpenseDate());
        expenseDTO.setNameExpense(expenseDTO.getNameExpense());

        return expenseDTO;
    }

    private Expense mapDTOToExpense(ExpenseDTO expenseDTO) {
        Expense expense = new Expense();
        expense.setAmountOfExpense(expenseDTO.getAmountOfExpense());

        expense.setNameExpense(expenseDTO.getNameExpense());
        expense.setExpenseDate(expenseDTO.getExpenseDate());

        return expense;
    }
}