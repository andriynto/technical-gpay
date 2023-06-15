package id.gpay.technical.transaction.application;

import id.gpay.technical.transaction.domain.dto.UserAccountDto;
import id.gpay.technical.transaction.domain.entity.UserAccount;
import id.gpay.technical.utility.dto.MessageResponse;

import java.util.List;

public interface UserAccountService {

    List<UserAccount> getAccountByUser(Long userId);

    UserAccount getByCardNumber(String cardNumber);

    MessageResponse saveAccountByUser(Long userId, UserAccountDto userAccountDto);

    MessageResponse deleteCardNumber(Long userId, String cardBNumber);

}
