package jamos.back.domain.instance.service;

import jamos.back.domain.instance.Instance;
import jamos.back.domain.instance.dto.InstanceRequestDto;
import jamos.back.domain.instance.repository.InstanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InstanceService {

    private final InstanceRepository instanceRepository;

    @Transactional
    public Instance createInstance(InstanceRequestDto requestDto) {
        Instance instance = Instance.createInstance(requestDto.getInstance_name(), requestDto.getInstance_name());

        return instanceRepository.save(instance);
    }

    public List<Instance> getInstances(Long userId) {
        return instanceRepository.findInstanceList(userId);
    }
}
