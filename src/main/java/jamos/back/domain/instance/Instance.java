package jamos.back.domain.instance;

import jamos.back.domain.jamdata.JamData;
import jamos.back.domain.useraccess.UserAccess;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PRIVATE)
public class Instance {

    @Id @GeneratedValue
    @Column(name = "instance_id")
    private Long id;

    @Column(name = "instance_name")
    private String name;

    @Column(name = "instance_type")
    private String type;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_id")
    private JamData data;

    LocalDateTime creationTime;

    LocalDateTime modifiedTime;

    @OneToMany(mappedBy = "instance")
    private Set<UserAccess> userAccesses;

    public void changeJamData(JamData data) {
        this.data = data;
    }

    public static Instance createInstance(String name, String type) {
        Instance instance = new Instance();
        instance.setName(name);
        instance.setType(type);

        LocalDateTime now = LocalDateTime.now();
        instance.setCreationTime(now);
        instance.setModifiedTime(now);
        instance.changeJamData(JamData.createJamData());

        return instance;
    }
}
