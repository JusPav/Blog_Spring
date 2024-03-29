package lt.code.academy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    private UUID id;
    @NotEmpty
    private String name;
    @NotEmpty(message="Email should not be empty or null")
    @Email
    private String email;
    @NotEmpty(message = "Message body should not be empty")
    private String content;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
