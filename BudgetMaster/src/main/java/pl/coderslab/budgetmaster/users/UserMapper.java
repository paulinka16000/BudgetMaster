package pl.coderslab.budgetmaster.users;


import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface UserMapper {


    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);

}
