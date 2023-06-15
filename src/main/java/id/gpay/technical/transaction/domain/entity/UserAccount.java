package id.gpay.technical.transaction.domain.entity;

import id.gpay.technical.transaction.domain.model.AccountStatus;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name= "user_account", schema = "public")
public class UserAccount {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @JoinColumn
    @ManyToOne
    private User user;

    @Column(name= "card_number", unique = true, length = 20)
    private String cardNumber;

    private double balance;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
