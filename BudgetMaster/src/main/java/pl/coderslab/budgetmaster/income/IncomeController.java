package pl.coderslab.budgetmaster.income;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.budgetmaster.expenses.ExpenseDTO;
import pl.coderslab.budgetmaster.users.User;
import pl.coderslab.budgetmaster.users.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/incomes")
@Slf4j
@RequiredArgsConstructor

public class IncomeController {
    private final IncomeService incomeService;
    private final UserRepository userRepository;
    private final IncomeMapper incomeMapper;

    private final IncomeRepository incomeRepository;

    @PostMapping("/create")
    public IncomeDTO createIncome(@RequestBody IncomeDTO incomeDTO) {
        return incomeService.createIncome(incomeDTO);
    }

    @GetMapping
    public ResponseEntity<List<IncomeDTO>> getAllIncomes() {
        List<IncomeDTO> incomes = incomeService.getAllIncomes();
        if (incomes.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {

            return ResponseEntity.ok(incomes);
        }
    }

    @GetMapping("/user/{userId}")
    public List<IncomeDTO> getIncomeByUserId(@PathVariable Long userId) {
        return incomeService.getIncomeByUserId(userId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncomeDTO> updateIncome(@PathVariable Long id, @RequestBody IncomeDTO incomeDTO) {
        incomeDTO.setId(id);
        IncomeDTO updatedIncome = incomeService.updateIncome(id, incomeDTO);
        if (updatedIncome != null) {
            return ResponseEntity.ok(updatedIncome);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncome(id);
        return ResponseEntity.noContent().build();
    }

    }
