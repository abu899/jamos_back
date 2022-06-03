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

    public Instance createInstance(InstanceRequestDto requestDto) {
        Instance instance = Instance.createInstance(requestDto.getInstanceName(), requestDto.getInstanceName());

        return instanceRepository.save(instance);
    }
}
