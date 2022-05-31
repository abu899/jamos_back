package jamos.back.domain.user.controller.form;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseForm {

    private Long id;
    private String email;
}
