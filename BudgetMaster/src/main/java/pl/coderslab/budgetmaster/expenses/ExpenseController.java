package pl.coderslab.budgetmaster.expenses;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/expense")
@Slf4j
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;


    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
        List<ExpenseDTO> expenses = expenseService.getAllExpenses();
        if (expenses.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {

            return ResponseEntity.ok(expenses);
        }
    }

    @GetMapping("/user/{userId}")
    public List<ExpenseDTO> getExpensesById(@PathVariable Long userId) {
        return expenseService.getExpensesByUserId(userId);
    }

    @PostMapping("/createExpense")
    public ExpenseDTO createExpense(@RequestBody ExpenseDTO expenseDTO) {
        return expenseService.createExpense(expenseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO expenseDTO) {
        expenseDTO.setId(id);
        ExpenseDTO updatedExpense = expenseService.updateExpense(id, expenseDTO);
        if (updatedExpense != null) {
            return ResponseEntity.ok(updatedExpense);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        try {
            expenseService.deleteExpense(id);
            return ResponseEntity.ok("Expense deleted successfully");
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping("/total/{userId}")
    public ResponseEntity<BigDecimal> getTotalExpensesByUserId(@PathVariable Long userId) {
        BigDecimal totalExpenses = expenseService.calculateTotalExpensesByUserId(userId);
        return ResponseEntity.ok(totalExpenses);
    }

    @GetMapping("/total")
    public ResponseEntity<BigDecimal> getTotalExpensesForAllUsers() {
        BigDecimal totalExpenses = expenseService.calculateTotalExpensesForAllUsers();
        BigDecimal expenseLimit = expenseService.getExpenseLimit();

            return ResponseEntity.ok(totalExpenses);

        }

    }

