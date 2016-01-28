package ua.com.jon.auth.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.com.jon.admin.shared.UserDTO;
import ua.com.jon.auth.domain.AssemblaUser;
import ua.com.jon.common.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/30/13
 */
public class UserMapper {
    public static UserDetails convertAssemblaToSpring(AssemblaUser assemblaUser) {
        return new org.springframework.security.core.userdetails.User(assemblaUser.getLogin(), assemblaUser.getLogin(), null);
    }

    public static ArrayList<UserDTO> assemblaToDtos(List<AssemblaUser> assemblaUsers) {
        ArrayList<UserDTO> userDTOs = new ArrayList<UserDTO>(assemblaUsers.size());
        for (AssemblaUser assemblaUser : assemblaUsers) {
            userDTOs.add(assemblaToDto(assemblaUser));
        }
        return userDTOs;
    }

    public static UserDTO assemblaToDto(AssemblaUser assemblaUser) {
        return new UserDTO(null, assemblaUser.getLogin());

    }

    public static UserDetails convertDBToSpring(User dbUser, Set<GrantedAuthority> roles) {
        return new org.springframework.security.core.userdetails.User(dbUser.getLogin(), dbUser.getPassword(), roles);
    }
}
