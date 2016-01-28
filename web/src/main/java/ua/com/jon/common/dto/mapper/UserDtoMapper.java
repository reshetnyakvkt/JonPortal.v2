package ua.com.jon.common.dto.mapper;

import ua.com.jon.admin.shared.UserDTO;
import ua.com.jon.common.domain.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 09.11.13
 * Time: 21:34
 * To change this template use File | Settings | File Templates.
 */
public class UserDtoMapper {

    public static HashSet<UserDTO> domainsToAdminDtos(Set<User> users) {
        HashSet<ua.com.jon.admin.shared.UserDTO> userDTOs = new HashSet<UserDTO>(users.size());
        for (User user : users) {
            userDTOs.add(domainToAdminDto(user));
        }
        return userDTOs;
    }

    public static UserDTO domainToAdminDto(User user) {
        return new UserDTO(user.getId(), user.getLogin());
    }
}
