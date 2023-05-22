package pl.coderslab.budgetmaster.income;

import org.springframework.stereotype.Service;
import pl.coderslab.budgetmaster.expenseCategory.ExpensesCategory;
import pl.coderslab.budgetmaster.expenses.Expense;
import pl.coderslab.budgetmaster.expenses.ExpenseDTO;
import pl.coderslab.budgetmaster.users.User;
import pl.coderslab.budgetmaster.users.UserRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;


@Service
public class IncomeService {
    private final IncomeRepository incomeRepository;
    private final IncomeMapper incomeMapper;
    private final UserRepository userRepository;

    public IncomeService(IncomeRepository incomeRepository, IncomeMapper incomeMapper, UserRepository userRepository) {
        this.incomeRepository = incomeRepository;
        this.incomeMapper = incomeMapper;
        this.userRepository = userRepository;
    }

    public IncomeDTO createIncome(IncomeDTO incomeDTO) {
        Income income = new Income();
        income.setAmountOfIncome(incomeDTO.getAmountOfIncome());
        income.setReceiptDate(incomeDTO.getReceiptDate());
        income.setSourceOfIncome(incomeDTO.getSourceOfIncome());


        Optional<User> userOptional = userRepository.findById(incomeDTO.getUserId());
        if (userOptional.isPresent()) {
            income.setUser(userOptional.get());
        } else {
            throw new RuntimeException("Invalid user ID");
        }

        Income savedIncome = incomeRepository.save(income);
        return mapIncomeToDTO(savedIncome);
    }

        private IncomeDTO mapIncomeToDTO(Income income) {
            IncomeDTO incomeDTO = new IncomeDTO();
            incomeDTO.setId(income.getId());
            incomeDTO.setSourceOfIncome(income.getSourceOfIncome());
            incomeDTO.setReceiptDate(income.getReceiptDate());
            incomeDTO.setAmountOfIncome(income.getAmountOfIncome());
            incomeDTO.setUserId(income.getUser().getId());
            return incomeDTO;
        }


    public List<IncomeDTO> getAllIncomes() {
        List<Income> incomes = incomeRepository.findAll();
        return incomes.stream()
                .map(this::mapIncomeToDTO)
                .collect(Collectors.toList());
    }



    public List<IncomeDTO> getIncomeByUserId(Long userId) {
        List<Income> incomes = incomeRepository.findByUserId(userId);
        return incomes.stream()
                .map(this::mapIncomeToDTO)
                .collect(Collectors.toList());
    }

    public IncomeDTO updateIncome(Long id, IncomeDTO incomeDTO) {
        Income incomeToUpdate = incomeRepository.findById(incomeDTO.getId())
                .orElseThrow(() -> new pl.coderslab.budgetmaster.income.NotFoundException("Income not found"));

        incomeToUpdate.setSourceOfIncome(incomeDTO.getSourceOfIncome());
        incomeToUpdate.setAmountOfIncome(incomeDTO.getAmountOfIncome());
        incomeToUpdate.setReceiptDate(incomeDTO.getReceiptDate());

        User user = userRepository.findById(incomeDTO.getUserId())
                .orElseThrow(() -> new pl.coderslab.budgetmaster.income.NotFoundException("User not found"));

        incomeToUpdate.setUser(user);

        Income updatedIncome = incomeRepository.save(incomeToUpdate);
        return mapIncomeToDTO(updatedIncome);
    }





    public void deleteIncome(Long id) {
        if (!incomeRepository.existsById(id)) {
            throw new NotFoundException("Income not found with id: " + id);
        }
        incomeRepository.deleteById(id);
    }
    }



