package id.gpay.technical.transaction.application;

import id.gpay.technical.transaction.domain.dto.UserAccountDto;
import id.gpay.technical.transaction.domain.dto.UserDto;
import id.gpay.technical.transaction.domain.entity.User;
import id.gpay.technical.transaction.domain.entity.UserAccount;
import id.gpay.technical.utility.dto.MessageResponse;
import id.gpay.technical.utility.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/userAccount")
public class UserAccountController {

    private final UserAccountService userAccountService;

    @Autowired
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccount> getUserById(@PathVariable(value = "id") String cardNumber) throws ResourceNotFoundException {
        return new ResponseEntity<UserAccount>(userAccountService.getByCardNumber(cardNumber), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<UserAccount>> getAccountByUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        return new ResponseEntity<List<UserAccount>>(userAccountService.getAccountByUser(userId), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<MessageResponse> saveAccountByUser(@PathVariable(value = "id") Long userId, @RequestBody @Valid UserAccountDto userAccountDto) {
        MessageResponse messageResponse = userAccountService.saveAccountByUser(userId, userAccountDto);

        return new ResponseEntity<>(messageResponse, messageResponse.getCode());
    }
}
