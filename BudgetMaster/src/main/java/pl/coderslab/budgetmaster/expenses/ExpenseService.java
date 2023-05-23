package pl.coderslab.budgetmaster.expenses;

import org.springframework.stereotype.Service;
import pl.coderslab.budgetmaster.expenseCategory.ExpensesCategory;
import pl.coderslab.budgetmaster.expenseCategory.ExpensesCategoryRepository;
import pl.coderslab.budgetmaster.users.User;
import pl.coderslab.budgetmaster.users.UserRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;
    private final UserRepository userRepository;

    private final ExpensesCategoryRepository expensesCategoryRepository;

    public ExpenseService(ExpenseRepository expenseRepository, ExpenseMapper expenseMapper, UserRepository userRepository, ExpensesCategoryRepository expensesCategoryRepository) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
        this.userRepository = userRepository;
        this.expensesCategoryRepository = expensesCategoryRepository;
    }

    public List<ExpenseDTO> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        return expenses.stream()
                .map(this::mapExpenseToDTO)
                .collect(Collectors.toList());
    }



    public List<ExpenseDTO> getExpensesByUserId(Long userId) {
        List<Expense> expenses = expenseRepository.findByUserId(userId);
        return expenses.stream()
                .map(this::mapExpenseToDTO)
                .collect(Collectors.toList());
    }

    public ExpenseDTO createExpense(ExpenseDTO expenseDTO) {
        Expense expense = new Expense();
        expense.setAmountOfExpense(expenseDTO.getAmountOfExpense());
        expense.setExpenseDate(expenseDTO.getExpenseDate());
        expense.setNameExpense(expenseDTO.getNameExpense());


        Optional<ExpensesCategory> categoryOptional = expensesCategoryRepository.findById(expenseDTO.getCategoriesId());
        if (categoryOptional.isPresent()) {
            expense.setExpenseCategory(categoryOptional.get());
        } else {

            throw new RuntimeException("Invalid expense category ID");
        }

        Optional<User> userOptional = userRepository.findById(expenseDTO.getUserId());
        if (userOptional.isPresent()) {
            expense.setUser(userOptional.get());
        } else {
            throw new RuntimeException("Invalid user ID");
        }

        Expense savedExpense = expenseRepository.save(expense);
        return mapExpenseToDTO(savedExpense);
    }

    private ExpenseDTO mapExpenseToDTO(Expense expense) {
        ExpenseDTO expenseDTO = new ExpenseDTO();
        expenseDTO.setId(expense.getId());
        expenseDTO.setAmountOfExpense(expense.getAmountOfExpense());
        expenseDTO.setExpenseDate(expense.getExpenseDate());
        expenseDTO.setNameExpense(expense.getNameExpense());
        expenseDTO.setCategoriesId(expense.getExpenseCategory().getId());
        expenseDTO.setUserId(expense.getUser().getId());
        return expenseDTO;
    }

    public ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO) {
        Expense existingExpense = expenseRepository.findById(expenseDTO.getId())
                .orElseThrow(() -> new NotFoundException("Expense not found with id: " + id));

        existingExpense.setNameExpense(expenseDTO.getNameExpense());
        existingExpense.setAmountOfExpense(expenseDTO.getAmountOfExpense());
        existingExpense.setExpenseDate(expenseDTO.getExpenseDate());


        User user = userRepository.findById(expenseDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        existingExpense.setUser(user);


        ExpensesCategory category = expensesCategoryRepository.findById(expenseDTO.getCategoriesId())
                .orElseThrow(() -> new NotFoundException("Expense Category not found"));


        existingExpense.setExpenseCategory(category);

        Expense updatedExpense = expenseRepository.save(existingExpense);
        return mapExpenseToDTO(updatedExpense);
    }


    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new NotFoundException("Expense not found with id: " + id);
        }
        expenseRepository.deleteById(id);

    }
}