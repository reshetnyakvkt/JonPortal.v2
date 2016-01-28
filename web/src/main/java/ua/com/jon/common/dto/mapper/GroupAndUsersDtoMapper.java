package ua.com.jon.common.dto.mapper;

import ua.com.jon.admin.shared.GroupAndUsersDTO;
import ua.com.jon.common.domain.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 09.11.13
 */
public class GroupAndUsersDtoMapper {

    public static ArrayList<GroupAndUsersDTO> domainsToDtos(List<Group> groups) {
        ArrayList<ua.com.jon.admin.shared.GroupAndUsersDTO> groupDTOs = new ArrayList<GroupAndUsersDTO>(groups.size());
        for (Group group : groups) {
            groupDTOs.add(GroupAndUsersDtoMapper.domainToDto(group));
        }
        return groupDTOs;
    }

    public static GroupAndUsersDTO domainToDto(Group group) {
        return new GroupAndUsersDTO(group.getId(), group.getName(), UserDtoMapper.domainsToAdminDtos(group.getUsers()), group.getCode());
    }
}
