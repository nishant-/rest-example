package rest.example.model.response;

import lombok.*;

import java.util.Date;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessage {

    private Date timeStamp;
    private String message;
}
