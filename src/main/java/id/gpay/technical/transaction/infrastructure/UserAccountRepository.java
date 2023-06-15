package id.gpay.technical.transaction.infrastructure;

import id.gpay.technical.transaction.domain.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    List<UserAccount> findByUserId(Long userId);

    Optional<UserAccount> findByCardNumber(String cardNumber);

    Boolean existsByCardNumber(String cardNumber);
}
