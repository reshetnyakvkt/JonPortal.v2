package ua.com.jon.auth.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;
import ua.com.jon.auth.domain.AssemblaSpace;
import ua.com.jon.auth.domain.AssemblaSpaces;
import ua.com.jon.auth.domain.AssemblaUser;
import ua.com.jon.auth.domain.SpringUser;
import ua.com.jon.auth.domain.UserRole;
import ua.com.jon.auth.exceptions.RestException;
import ua.com.jon.auth.util.UserMapper;
import ua.com.jon.common.domain.Group;
import ua.com.jon.common.domain.User;
import ua.com.jon.common.dto.GroupDTO;
import ua.com.jon.common.dto.mapper.GroupDtoMapper;
import ua.com.jon.common.repository.GroupRepository;
import ua.com.jon.common.repository.UserRepository;
import ua.com.jon.utils.RestClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/28/13
 */
@Service
public class AuthServiceImpl implements UserDetailsService, AuthService, UserDetailsManager {
    @Autowired
    private RestClient restClient;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    private static Logger log = Logger.getLogger(AuthServiceImpl.class);
    /*
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("User login: " + userName);
        SpringUser user;
        try {
            AssemblaUser assemblaUser = restClient.getAssemblaUser(userName);
            user = UserMapper.convertAssemblaToSpring(assemblaUser);
        } catch (ResourceAccessException e) {
            user = new SpringUser(userName, userName);
        } catch (Exception e) {
            log.error("Error user authentication " + userName, e);
            throw new UsernameNotFoundException("User/Password incorrect");
        }
        log.info("Authenticated user info: " + user);

        return user;
    }
    */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("User login: " + userName);
        UserDetails springUser;
        try {
            //AssemblaUser assemblaUser = restClient.getAssemblaUser(userName);
            //user = UserMapper.convertAssemblaToSpring(assemblaUser);
            User user = userRepository.findByUserNameWithRoles(userName);
            Set<GrantedAuthority> roles = new HashSet();
            for (UserRole role : user.getRoles()) {
                roles.add(new SimpleGrantedAuthority(role.name()));
            }
            springUser = UserMapper.convertDBToSpring(user, roles);
        } catch (ResourceAccessException e) {
            springUser = new SpringUser(userName, userName);
        } catch (Exception e) {
            log.error("Error user authentication " + userName, e);
            throw new BadCredentialsException("Логин или пароль не верные");
        }
        log.info("Authenticated user info: " + springUser);

        return springUser;
    }

    @Override
    public boolean isAuth(String space, String userName) {
        boolean res = false;
        AssemblaUser assemblaUser = null;
        try {
            assemblaUser = restClient.getUserBySpaceAndUName(space, userName);
        } catch (RestException e) {
            log.error(e);
        }
        if(assemblaUser != null) {
            res = true;
        }
        return res;
    }

/*    @Override
    public List<GroupDTO> getActiveGroups() {
        List<Group> activeGroups = groupRepository.findByActiveTrue();
        return GroupDtoMapper.domainToCommonDtos(activeGroups);
    }*/

    @Override
    public AssemblaUser getAssemblaUser(String space, String userName) {
        AssemblaUser assemblaUser = null;
        try {
            assemblaUser = restClient.getUserBySpaceAndUName(space, userName);
        } catch (ResourceAccessException e) {
            assemblaUser = new AssemblaUser(userName);
        } catch (RestException e) {
            log.error(e);
        }
        return assemblaUser;
    }

    @Override
    public User getDBUser(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public boolean isUserInGroup(User user, Long groupId) {
        Set<Group> groups = userRepository.findWithGroupsByUserName(user.getLogin()).getGroups();
        for (Group group : groups) {
            if (groupId.equals(group.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public void addUserToGroup(User usr, Long id) {
        Group group = groupRepository.findOne(id);
//        User user = userRepository.findWithGroupsByUserName(usr.getLogin());
        usr.getGroups().add(group);
        group.getUsers().add(usr);
        groupRepository.save(group);
//        userRepository.save(user);
    }

    @Override
    public List<GroupDTO> getGroupsById(Long groupId) {
        List<GroupDTO> groups = new ArrayList<GroupDTO>(1);
        groups.add(GroupDtoMapper.domainToCommonDto(groupRepository.findOne(groupId)));
        return groups;
    }

    @Override
    public List<AssemblaUser> getUsersBySpace(String spaceName) {
        try {
            return restClient.getUserListBySpace(spaceName);
        } catch (RestException e) {
            log.error(e);
        }
        return new ArrayList<AssemblaUser>();
    }

    @Override
    public AssemblaSpace getSpace(String spaceName) {
        AssemblaSpace assemblaSpace = null;
        try {
            assemblaSpace = restClient.getSpace(spaceName);
        } catch (RestException e) {
            log.error(e);
        }
        return assemblaSpace;
    }

//    public List<AssemblaUser> getUsers() {
//        assemblaUser = restClient.get;
//    }
    @Override
    public List<AssemblaSpace> getSpaces() {
        List<AssemblaSpace> spacesList = new ArrayList<AssemblaSpace>();
        try {
            AssemblaSpaces spaces = restClient.getSpaces();
            return spaces.getSpaces();
        } catch (RestException e) {
            log.error(e);
        }
        return spacesList;
    }

    @Override
    public User createNewUser(String login, String password, List<GroupDTO> groups) {
        Set<Long> groupIds = new HashSet<Long>();
        for (GroupDTO group : groups) {
            groupIds.add(group.getId());
        }
        Iterable<Group> groupIter = groupRepository.findAll(groupIds);
        Set<Group> groupSet = new HashSet<Group>();
        for (Group group : groupIter) {
            groupSet.add(group);
        }
        User user = new User(login, password, new Date(), groupSet, Collections.singleton(UserRole.USER), false);
        return userRepository.save(user);
    }

    @Override
    public User createNewUser(String login, String password) {
        User user = new User(login, password, new Date(), null, Collections.singleton(UserRole.USER), false);
        return userRepository.save(user);
    }

    @Override
    public User getUserFromDBByName(String login) {
        return userRepository.findWithGroupsByUserName(login);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void createUser(UserDetails userDetails) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateUser(UserDetails userDetails) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteUser(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void changePassword(String s, String s2) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean userExists(String s) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
