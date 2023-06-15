package id.gpay.technical.transaction.application;

import id.gpay.technical.transaction.domain.dto.UserDto;
import id.gpay.technical.transaction.domain.entity.User;
import id.gpay.technical.transaction.domain.model.WebResponse;
import id.gpay.technical.utility.dto.MessageResponse;
import id.gpay.technical.utility.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<List<User>>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<MessageResponse> createUser(@RequestBody @Valid UserDto userDto) {
        MessageResponse messageResponse = userService.createUser(userDto);

        return new ResponseEntity<>(messageResponse, messageResponse.getCode());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateUser(@PathVariable(value = "id") Long id, @RequestBody @Valid UserDto userDto) {
        MessageResponse messageResponse = userService.updateUser(id, userDto);

        return new ResponseEntity<>(messageResponse, messageResponse.getCode());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.ACCEPTED);
    }
}
