package pl.coderslab.budgetmaster.expenseCategory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses-categories")
@Slf4j
@RequiredArgsConstructor
public class ExpensesCategoryController {
    private final ExpensesCategoryService expensesCategoryService;

    @PostMapping
    public ResponseEntity<ExpensesCategoryDTO> createExpensesCategory(@RequestBody ExpensesCategoryDTO expensesCategoryDTO) {
        ExpensesCategoryDTO createdExpensesCategory = expensesCategoryService.createExpensesCategory(expensesCategoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpensesCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpensesCategoryDTO> getExpensesCategoryById(@PathVariable Long id) {
        ExpensesCategoryDTO expensesCategoryDTO = expensesCategoryService.getExpensesCategoryById(id);
        if (expensesCategoryDTO != null) {
            return ResponseEntity.ok(expensesCategoryDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ExpensesCategoryDTO>> getAllExpensesCategories() {
        List<ExpensesCategoryDTO> expensesCategories = expensesCategoryService.getAllExpensesCategories();
        return ResponseEntity.ok(expensesCategories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpensesCategoryDTO> updateCategory(@PathVariable Long id, @RequestBody ExpensesCategoryDTO categoryDTO) {
        ExpensesCategoryDTO updatedCategory = expensesCategoryService.updateCategory(id, categoryDTO);
        if (updatedCategory != null) {
            return ResponseEntity.ok(updatedCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpensesCategoryById(@PathVariable Long id) {
        expensesCategoryService.deleteExpensesCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}