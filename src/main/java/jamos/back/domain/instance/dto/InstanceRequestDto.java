package jamos.back.domain.instance.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class InstanceRequestDto {

    private String instanceName;
    private String instanceType;
    private List<String> readableUsers;
    private List<String> writableUsers;
}
