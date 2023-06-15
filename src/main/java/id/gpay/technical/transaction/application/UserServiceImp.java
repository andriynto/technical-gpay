package id.gpay.technical.transaction.application;

import id.gpay.technical.transaction.domain.dto.UserDto;
import id.gpay.technical.transaction.domain.entity.User;
import id.gpay.technical.transaction.infrastructure.UserRepository;
import id.gpay.technical.transaction.security.BCrypt;
import id.gpay.technical.utility.dto.MessageResponse;
import id.gpay.technical.utility.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class, "id", id.toString()));
    }

    @Override
    public MessageResponse createUser(UserDto userDto) {

        if(userRepository.existsByEmail(userDto.getEmail())) {
            return new MessageResponse("email already registered", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByUsername(userDto.getUsername())) {
            return new MessageResponse("username already registered", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsBySid(userDto.getSid())) {
            return new MessageResponse("Phone number already registered", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setSid(userDto.getSid());
        user.setUsername(userDto.getUsername());
        user.setPassword(BCrypt.hashpw("secret", BCrypt.gensalt()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
        return new MessageResponse("User success created", HttpStatus.OK);
    }

    @Override
    public MessageResponse updateUser(Long id, UserDto userDto) {
        User user = getUserById(id);
        user.setName(userDto.getName());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
        return new MessageResponse("User success updated", HttpStatus.OK);
    }

    @Override
    public MessageResponse deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return new MessageResponse("User success deleted", HttpStatus.ACCEPTED);
        } else {
            throw new ResourceNotFoundException(User.class, "id", id.toString());
        }
    }

}
