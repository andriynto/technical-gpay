package id.gpay.technical.transaction.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name= "user", schema = "public")
public class User {
    @Id
    @Column(name= "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "name", nullable = false)
    private String name;

    @Column(name= "email", nullable = false, unique = true)
    private String email;

    @Column(name= "sid", nullable = false, unique = true)
    private String sid;

    @JsonIgnore
    @Column(name= "username", nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(name= "password", nullable = false)
    private String password;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
