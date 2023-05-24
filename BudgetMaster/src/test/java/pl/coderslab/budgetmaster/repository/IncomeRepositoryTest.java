package pl.coderslab.budgetmaster.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pl.coderslab.budgetmaster.income.Income;
import pl.coderslab.budgetmaster.income.IncomeRepository;
import pl.coderslab.budgetmaster.users.User;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
@DataJpaTest

public class IncomeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IncomeRepository incomeRepository;


    @Test
    public void testCalculateTotalIncomesByUserId() {
        // Given
        final Long userId = 1L;

        User user = new User();
        user.setId(userId);
        user.setUserFirstName("Rafał");
        entityManager.merge(user);


        Income income1 = new Income();
        income1.setUser(user);
        income1.setAmountOfIncome(new BigDecimal("6500.00"));
        income1.setReceiptDate(LocalDate.now());
        income1.setSourceOfIncome("SALARY");

        entityManager.persist(income1);

        Income income2 = new Income();
        income2.setUser(user);
        income2.setAmountOfIncome(new BigDecimal("3500.00"));
        income2.setReceiptDate(LocalDate.now());
        income2.setSourceOfIncome("SALARY2");

        entityManager.persist(income2);

        entityManager.flush();

        // When
        BigDecimal totalIncome = incomeRepository.calculateTotalIncomesByUserId(userId);

        // Then
        BigDecimal expectedTotalIncomes = new BigDecimal("10000.00");
        assertEquals(expectedTotalIncomes, totalIncome);
    }
}


