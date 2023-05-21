package pl.coderslab.budgetmaster.expenses;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
@Slf4j
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @GetMapping("/getAllExpenses")
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
        List<ExpenseDTO> expenses = expenseService.getAllExpenses();
        if (expenses.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {

        return ResponseEntity.ok(expenses);
        }
    }
    @GetMapping("/getExpenseByUserId/{user_id}")
    public ResponseEntity<ExpenseDTO> getExpenseByUserId(@PathVariable Long user_id) {
        ExpenseDTO expense = expenseService.getExpenseByUserId(user_id);
        if (expense != null) {
            return ResponseEntity.ok(expense);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/createExpense")
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expenseDTO) {
        ExpenseDTO createdExpense = expenseService.createExpense(expenseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
    }

    @PutMapping("/updateExpense/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO expenseDTO) {
        ExpenseDTO updatedExpense = expenseService.updateExpense(id, expenseDTO);
        if (updatedExpense != null) {
            return ResponseEntity.ok(updatedExpense);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteExpense/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        boolean deleted = expenseService.deleteExpense(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
