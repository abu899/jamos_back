package jamos.back.domain.user.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
public class RequestUser {

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;
}
