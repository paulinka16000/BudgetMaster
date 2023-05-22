package pl.coderslab.budgetmaster.income;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")

public interface IncomeMapper {
    IncomeDTO incomeDTO(Income income);

    Income income(IncomeDTO incomeDTO);

    @Mapping(target = "user", ignore = true)
    void updateIncomeFromDTO(IncomeDTO incomeDTO, @MappingTarget Income income);
}


