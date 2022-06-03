package jamos.back.domain.user;

import jamos.back.domain.useraccess.UserAccess;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(name = "user_name")
    private String name;

    private String password;

    @OneToMany(mappedBy = "user")
    private Set<UserAccess> userAccesses;

    public User(String email, String name, String password) {

        this.email = email;
        this.name = name;
        this.password = password;
    }
}
