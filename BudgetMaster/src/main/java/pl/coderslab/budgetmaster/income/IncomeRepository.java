package pl.coderslab.budgetmaster.income;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByUserId(Long userId);

    @Query("SELECT SUM(e.amountOfIncome) FROM Income e WHERE e.user.id = :userId")
    BigDecimal calculateTotalIncomesByUserId(@Param("userId") Long userId);

}
