package jamos.back.domain.user.controller.form;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestForm {

    @NotBlank
    @Email
    @Length(max = 50)
    private String loginId;

    @NotBlank
    @Length(max = 15)
    private String password;
}
