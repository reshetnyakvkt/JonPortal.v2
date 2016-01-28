package ua.com.jon.common.service;

import org.springframework.stereotype.Service;
import ua.com.jon.common.domain.Group;
import ua.com.jon.common.dto.GroupDTO;
import ua.com.jon.common.dto.mapper.GroupDtoMapper;
import ua.com.jon.common.repository.GroupRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 25.07.14
 */
@Service
public class RegisterService {
    @Resource
    private GroupRepository groupRepository;

    public List<GroupDTO> getActiveGroups() {
        List<Group> activeGroups = groupRepository.findByActiveTrueOrderByStartDateDesc();
        return GroupDtoMapper.domainToCommonDtos(activeGroups);
    }
}