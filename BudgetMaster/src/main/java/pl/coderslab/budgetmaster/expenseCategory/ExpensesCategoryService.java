package pl.coderslab.budgetmaster.expenseCategory;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpensesCategoryService {
    private final ExpensesCategoryRepository expensesCategoryRepository;
    private final ExpensesCategoryMapper expensesCategoryMapper;



    public ExpensesCategoryService(ExpensesCategoryRepository expensesCategoryRepository, ExpensesCategoryMapper expensesCategoryMapper) {
        this.expensesCategoryRepository = expensesCategoryRepository;
        this.expensesCategoryMapper = expensesCategoryMapper;
    }

    public ExpensesCategoryDTO createExpensesCategory(ExpensesCategoryDTO expensesCategoryDTO) {

        ExpensesCategory expensesCategory = expensesCategoryMapper.mapToEntity(expensesCategoryDTO);
        ExpensesCategory savedExpensesCategory = expensesCategoryRepository.save(expensesCategory);
        return expensesCategoryMapper.mapToDTO(savedExpensesCategory);
    }

    public ExpensesCategoryDTO getExpensesCategoryById(Long id) {
        Optional<ExpensesCategory> expensesCategoryOptional = expensesCategoryRepository.findById(id);
        return expensesCategoryOptional.map(expensesCategoryMapper::mapToDTO).orElse(null);
    }

    public List<ExpensesCategoryDTO> getAllExpensesCategories() {
        List<ExpensesCategory> expensesCategories = expensesCategoryRepository.findAll();
        return expensesCategories.stream()
                .map(expensesCategoryMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public ExpensesCategoryDTO updateCategory(Long id, ExpensesCategoryDTO categoryDTO) {
        Optional<ExpensesCategory> categoryOptional = expensesCategoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            ExpensesCategory category = categoryOptional.get();
            category.setName(categoryDTO.getName());
            category.setName(categoryDTO.getName());
            ExpensesCategory updatedCategory = expensesCategoryRepository.save(category);
            return expensesCategoryMapper.mapToDTO(updatedCategory);
        } else {
            return null;
        }
    }


    public void deleteExpensesCategoryById(Long id) {
        expensesCategoryRepository.deleteById(id);
    }
}
