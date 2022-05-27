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

    private boolean readable;

    private boolean writeable;

    private boolean ownership;

    public UserAccess(User user, Instance instance, boolean readable, boolean writeable, boolean ownership) {
        this.user = user;
        this.instance = instance;
        this.readable = readable;
        this.writeable = writeable;
        this.ownership = ownership;
    }

    //생성 메서드
    public static UserAccess createUserAccess(User user,
                                              Instance instance,
                                              boolean readable,
                                              boolean writeable,
                                              boolean ownership) {

        return new UserAccess(user, instance, readable, writeable, ownership);
    }
}
