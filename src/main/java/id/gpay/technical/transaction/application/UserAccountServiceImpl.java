package id.gpay.technical.transaction.application;

import id.gpay.technical.transaction.domain.dto.UserAccountDto;
import id.gpay.technical.transaction.domain.entity.User;
import id.gpay.technical.transaction.domain.entity.UserAccount;
import id.gpay.technical.transaction.domain.model.AccountStatus;
import id.gpay.technical.transaction.infrastructure.UserAccountRepository;
import id.gpay.technical.transaction.infrastructure.UserRepository;
import id.gpay.technical.utility.dto.MessageResponse;
import id.gpay.technical.utility.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(UserRepository userRepository, UserAccountRepository userAccountRepository) {
        this.userRepository = userRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public List<UserAccount> getAccountByUser(Long userId) {
        return userAccountRepository.findByUserId(userId);
    }

    @Override
    public UserAccount getByCardNumber(String cardNumber) {
        return userAccountRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new ResourceNotFoundException(UserAccount.class, "card number", cardNumber));
    }

    @Override
    public MessageResponse saveAccountByUser(Long userId, UserAccountDto userAccountDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found"));

        if(userAccountRepository.existsByCardNumber(userAccountDto.getCardNumber())) {
            return new MessageResponse("Card number already added", HttpStatus.BAD_REQUEST);
        }

        UserAccount userAccount = new UserAccount();
        userAccount.setId(UUID.randomUUID().toString());
        userAccount.setUser(user);
        userAccount.setCardNumber(userAccountDto.getCardNumber());
        userAccount.setBalance(userAccountDto.getBalance());
        userAccount.setAccountStatus(AccountStatus.ACTIVE);
        userAccount.setCreatedAt(LocalDateTime.now());
        userAccount.setUpdatedAt(LocalDateTime.now());

        userAccountRepository.save(userAccount);
        return new MessageResponse("Card success added", HttpStatus.OK);
    }

    @Override
    public MessageResponse deleteCardNumber(Long userId, String cardBNumber) {
        return null;
    }
}
