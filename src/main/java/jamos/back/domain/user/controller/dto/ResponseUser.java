package jamos.back.domain.user.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
public class ResponseUser {

    @NotEmpty
    private Long id;

    @NotEmpty
    private String loginId;
}
