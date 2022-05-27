package jamos.back.domain.instance.controller;

import jamos.back.domain.instance.dto.InstanceRequestDto;
import jamos.back.domain.instance.service.InstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InstanceController {

    private final InstanceService instanceService;

    @PostMapping("/instance")
    public void makeInstance(@RequestBody @Validated InstanceRequestDto requestDto){
        instanceService.createInstance(requestDto);
    }

}
