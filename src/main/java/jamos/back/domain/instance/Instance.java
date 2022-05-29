package jamos.back.domain.instance;

import jamos.back.domain.jamdata.JamData;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Instance {

    @Id @GeneratedValue
    @Column(name = "instance_id")
    private Long id;

    @Column(name = "instance_name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_id")
    private JamData data;

    LocalDateTime creationTime;

    LocalDateTime modifiedTime;

}
