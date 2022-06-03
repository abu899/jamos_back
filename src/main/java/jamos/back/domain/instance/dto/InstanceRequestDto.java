package jamos.back.domain.instance.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class InstanceRequestDto {

    private String instanceName;
    private String instanceType;
}
