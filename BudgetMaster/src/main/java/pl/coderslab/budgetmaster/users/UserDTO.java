package pl.coderslab.budgetmaster.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDTO {

    @Schema(description = "User id", example = "1")
    private Long id;
    @Schema(description = "User First Name", example = "Paulina")
    private String userFirstName;
}
