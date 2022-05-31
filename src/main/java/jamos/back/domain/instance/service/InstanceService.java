package jamos.back.domain.instance.service;

import jamos.back.domain.instance.Instance;
import jamos.back.domain.instance.dto.InstanceRequestDto;
import jamos.back.domain.instance.repository.InstanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstanceService {

    private final InstanceRepository instanceRepository;

    public Long createInstance(InstanceRequestDto requestDto) {
        Instance instance = Instance.createInstance(requestDto.getInstanceName(), requestDto.getInstanceName());
        Instance savedInstance = instanceRepository.save(instance);

        return savedInstance.getId();
    }
}
