package jamos.back.domain.jamdata;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
public class JamData {

    @Id @GeneratedValue
    @Column(name = "data_id")
    private Long id;

    private String fileList = "";

    private String dirList = "";

    private String jsonData = "";

    public static JamData createJamData() {
        return new JamData();
    }
}
