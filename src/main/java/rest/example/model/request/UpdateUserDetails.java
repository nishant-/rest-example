package rest.example.model.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDetails {


        @NotNull(message = "First name cannot be null")
        private String firstName;
        @NotNull(message = "Last name cannot be null")
        private String lastName;
}
