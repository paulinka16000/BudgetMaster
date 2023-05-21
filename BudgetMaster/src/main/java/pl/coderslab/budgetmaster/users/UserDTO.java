package pl.coderslab.budgetmaster.users;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDTO {
    private Long id;
    private String userFirstName;
}
