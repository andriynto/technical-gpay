package id.gpay.technical.transaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import id.gpay.technical.transaction.domain.dto.UserDto;
import id.gpay.technical.transaction.domain.model.WebResponse;
import id.gpay.technical.transaction.infrastructure.UserRepository;
import id.gpay.technical.transaction.security.BCrypt;
import id.gpay.technical.utility.dto.MessageResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testRegisterSuccess() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("Andryanto");
        userDto.setSid("081377239880");
        userDto.setEmail("andriyanto@indonusamedia.co.id");
        userDto.setUsername("andriyanto");
        userDto.setPassword(BCrypt.hashpw("secret", BCrypt.gensalt()));

        mockMvc.perform(
                post("/api/v1/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            MessageResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});
            assertEquals("User success created", response.getMessage());
        });
    }

    @Test
    void testRegisterBadRequest() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("");
        userDto.setSid("");
        userDto.setEmail("");
        userDto.setUsername("");
        userDto.setPassword("");

        mockMvc.perform(
                post("/api/v1/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            MessageResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

            assertEquals("User success created", response.getMessage());
        });
    }
}
