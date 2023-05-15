package lt.code.academy.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="posts")
public class PostEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String title;
    private String url;
    @Column(nullable = false)
    private String content;
    private String shortDescription;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;

    @OneToMany(mappedBy = "post",cascade = CascadeType.REMOVE)
    private Set<CommentEntity> comments=new HashSet<>();
}
