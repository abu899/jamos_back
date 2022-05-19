package jamos.back.domain.login.controller.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequestForm {

    @NotBlank
    @Email
    @Length(max = 50)
    private String loginId;

    @NotBlank
    @Length(max = 15)
    private String password;
}
