package jamos.back.domain.user;

import jamos.back.domain.instance.Instance;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAccess {

    @Id
    @GeneratedValue
    @Column(name = "user_access_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instance_id")
    private Instance instance;

    @Enumerated(EnumType.STRING)
    Rights rights;

    public UserAccess(User user, Instance instance, Rights rights) {
        this.user = user;
        this.instance = instance;
        this.rights = rights;
    }

    //생성 메서드
    public static UserAccess createUserAccess(User user,
                                              Instance instance,
                                              Rights rights) {

        return new UserAccess(user, instance, rights);
    }
}
