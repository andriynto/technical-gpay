package id.gpay.technical.transaction.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import id.gpay.technical.transaction.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Not;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccountDto {

    @JsonIgnore
    String id;

    @NotNull
    User userId;

    @NotNull
    @Size(max = 20, min = 5)
    String cardNumber;

    @NotNull
    double balance;
}
