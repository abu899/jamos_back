package jamos.back.domain.useraccess;

import jamos.back.domain.instance.Instance;
import jamos.back.domain.user.Rights;
import jamos.back.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Setter(AccessLevel.PRIVATE)
    @Enumerated(EnumType.STRING)
    Rights rights;

    private void addUser(User user) {
        this.user = user;
        user.getUserAccesses().add(this);
    }

    private void addInstance(Instance instance) {
        this.instance = instance;
        instance.getUserAccesses().add(this);
    }

    //생성 메서드
    public static UserAccess createUserAccess(User user,
                                              Instance instance,
                                              Rights rights) {
        UserAccess userAccess = new UserAccess();
        userAccess.addUser(user);
        userAccess.addInstance(instance);
        userAccess.setRights(rights);
        return userAccess;
    }
}
