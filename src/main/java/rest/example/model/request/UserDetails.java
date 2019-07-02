package rest.example.model.request;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class UserDetails {

    @NotNull(message = "First name cannot be null")
    private String firstName;
    @NotNull(message = "Last name cannot be null")
    private String lastName;
    @NotNull(message = "email cannot be null")
    @Email
    private String email;
    @NotNull (message = "password cannot be null")
    @Size(min = 8, max = 16, message = "password must be equal or greater than 8 characters")
    private String password;
}
