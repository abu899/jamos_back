package jamos.back.domain.user.controller.form;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseForm {

    private Long id;
    private String loginId;
}
