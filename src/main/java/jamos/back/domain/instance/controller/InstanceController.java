package jamos.back.domain.instance.controller;

import jamos.back.domain.instance.dto.InstanceRequestDto;
import jamos.back.domain.instance.dto.InstanceResponseDto;
import jamos.back.domain.instance.service.InstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InstanceController {

    private final InstanceService instanceService;

    @PostMapping("/instance")
    public ResponseEntity<InstanceResponseDto> makeInstance(@RequestBody @Validated InstanceRequestDto requestDto,
                                                            BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new InstanceResponseDto(false));
        }

        Long instanceId = instanceService.createInstance(requestDto);

        if (instanceId == -1) {
            return ResponseEntity.badRequest().body(new InstanceResponseDto(false));
        }

        return ResponseEntity.ok(new InstanceResponseDto(true));
    }

}
