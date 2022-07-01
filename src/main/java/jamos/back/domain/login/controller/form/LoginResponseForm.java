package jamos.back.domain.login.controller.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseForm {

    private boolean success;
    private String token;

    public LoginResponseForm(boolean success) {
        this.success = success;
        this.token = "";
    }
}
