package lt.code.academy.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private UUID id;
    @NotEmpty(message="Post title should not be empty")
    private String title;
    private String url;
    @NotEmpty(message="Post content should not be empty")
    private String content;
    @NotEmpty(message="Post short description should not be empty")
    private String shortDescription;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Set<Comment> comments;
}
