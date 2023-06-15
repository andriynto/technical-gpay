package id.gpay.technical.transaction.application;

import id.gpay.technical.transaction.domain.dto.UserDto;
import id.gpay.technical.transaction.domain.entity.User;
import id.gpay.technical.utility.dto.MessageResponse;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    User getUserById(Long id);

    MessageResponse createUser(UserDto userDto);

    MessageResponse updateUser(Long id, UserDto userDto);

    MessageResponse deleteUser(Long id);
}
