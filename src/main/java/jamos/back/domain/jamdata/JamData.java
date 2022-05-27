package jamos.back.domain.jamdata;

import jamos.back.domain.instance.Instance;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class JamData {

    @Id @GeneratedValue
    @Column(name = "data_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "data")
    private Instance instance;

    private String fileList;

    private String dirList;

    private String jsonData;
}
