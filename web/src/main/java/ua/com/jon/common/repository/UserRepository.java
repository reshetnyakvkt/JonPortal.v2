package ua.com.jon.common.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.com.jon.common.domain.User;

import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/27/13
 */
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("select u from User u JOIN u.groups gs where gs.name = :groupName")
    List<User> findByGroupName(@Param("groupName") String groupName);

    @Query("select distinct u from User u JOIN FETCH u.groups where u.login in (:names)")
    List<User> findByNamesWithGroups(@Param("names") Collection<String> names);

    @Query("select distinct u from User u where u.login in (:names)")
    List<User> findByNames(@Param("names") Collection<String> names);

    @Query("select u from User u JOIN FETCH u.groups where u.login = :name")
    User findWithGroupsByUserName(@Param("name") String name);

    @Query("select u from User u where u.login = :name")
    User findByUserName(@Param("name") String name);

    @Query("select u from User u LEFT JOIN FETCH u.roles rs LEFT JOIN FETCH u.groups gs " +
            "LEFT JOIN FETCH u.tasks ts where u.login = :userName")
    User findByUserNameWithRoles(@Param("userName") String userName);

    //@Query("select u from User u where u.login = :name")
    User findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    @Query("select u from User u JOIN u.groups gs where gs.id = :groupId AND u.ignore = false")
    List<User> findByGroupIdIgnore(@Param("groupId") Long groupId, Pageable pageable);
}
