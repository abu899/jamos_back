package jamos.back.domain.user;


import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

    public User(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
