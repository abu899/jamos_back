package jamos.back.domain.instance.controller;

import jamos.back.domain.SessionConst;
import jamos.back.domain.instance.Instance;
import jamos.back.domain.instance.dto.InstanceRequestDto;
import jamos.back.domain.instance.dto.InstanceResponseDto;
import jamos.back.domain.instance.service.InstanceService;
import jamos.back.domain.useraccess.UserAccess;
import jamos.back.domain.useraccess.service.UserAccessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class InstanceController {

    private final InstanceService instanceService;
    private final UserAccessService userAccessService;

    @PostMapping("/instance")
    public ResponseEntity<InstanceResponseDto> makeInstance(@RequestBody @Validated InstanceRequestDto requestDto,
                                                            BindingResult bindingResult,
                                                            HttpServletRequest request){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new InstanceResponseDto(false));
        }

        Instance instance = instanceService.createInstance(requestDto);

        HttpSession session = request.getSession(false);
        log.info("response session id = {} ", session.getId());
        Long user_id = (Long)request.getSession().getAttribute(SessionConst.LOGIN_USER_ID);
        log.info("user_id = {}", user_id);

        UserAccess userAccess = userAccessService.create(user_id, instance);

        if (null == userAccess) {
            return ResponseEntity.badRequest().body(new InstanceResponseDto(false));
        }

        return ResponseEntity.ok(new InstanceResponseDto(true));
    }

    @GetMapping("/instance")
    public ResponseEntity<InstanceResponseDto> getInstances(HttpServletRequest request,
                                                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new InstanceResponseDto(false));
        }

        Long user_id = (Long)request.getSession().getAttribute(SessionConst.LOGIN_USER_ID);
        log.info("user_id = {}", user_id);

        List<Instance> instances = instanceService.getInstances(user_id);
        for (Instance instance : instances) {
            log.info("instance.getName() = {}, instance.getCreationTime() = {}",
                    instance.getName(), instance.getCreationTime());
        }

        return ResponseEntity.ok(new InstanceResponseDto(true));
    }

}
