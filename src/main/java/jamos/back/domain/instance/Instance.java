package jamos.back.domain.instance;

import jamos.back.domain.instance.dto.InstanceRequestDto;
import jamos.back.domain.jamdata.JamData;
import jamos.back.domain.user.UserAccess;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Instance {

    @Id @GeneratedValue
    @Column(name = "instance_id")
    private Long id;

    @Column(name = "instance_name")
    private String name;

    @OneToMany(mappedBy = "instance")
    private List<UserAccess> userAccesses;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_id")
    private JamData data;

}
