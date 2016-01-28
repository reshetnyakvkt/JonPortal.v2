package ua.com.jon.common.dao;

import ua.com.jon.admin.shared.GroupAndSprintsDTO;
import ua.com.jon.common.domain.Group;
import ua.com.jon.common.dto.GroupDTO;

import java.util.List;


/**
 * Date: 01.01.13
 * Time: 23:58
 */
public interface GroupDAO {
    List<GroupDTO> findActiveGroupAndTasksAndUsers();

    List<List<String>> findByGroupIdAndUserNotIgnore(Long selectedGroupId) throws Exception;

    List<GroupAndSprintsDTO> findActiveGroupsAndSprints();
}
