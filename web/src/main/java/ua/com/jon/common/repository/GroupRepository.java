package ua.com.jon.common.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.com.jon.common.domain.Group;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/26/13
 */
public interface GroupRepository extends CrudRepository<Group, Long> {

    @Query("select g from Group g where g.name = ?1")
    Group findByName(String name);

    @Query("select distinct g from Group g JOIN FETCH g.users")
    List<Group> findAllGroupsAndUsers ();

    @Query("select g from Group g JOIN FETCH g.users JOIN FETCH g.data where g.name = ?1")
    Group findGroupAndUsersAndTasksByName(String groupName);

    @Query("select g from Group g JOIN FETCH g.users where g.name = ?1")
    Group findGroupAndUsersByName(String groupName);

    @Query("select g from Group g JOIN FETCH g.users where g.id = ?1")
    Group findGroupAndUsers(Long id);

    @Query("select distinct g from Group g JOIN FETCH g.data where g.active = true")
    List<Group> findActiveGroupAndTestTasks();

    @Query("select distinct g from Group g JOIN FETCH g.data JOIN FETCH g.users where g.active = true")
    List<Group> findActiveGroupAndTasksAndUsers();

//    @Query("SELECT fav FROM Favourite fav join fetch fav.colors as cl WHERE EXISTS " +
//            "( SELECT fav2 FROM Favourite fav2 join fetch fav2.colors as cl2 WHERE fav2.id = fav.id AND cl2.name = \"red\" )")
    @Query("select g from Group g JOIN FETCH g.users us WHERE EXISTS " +
            "(select g1 FROM Group g1 JOIN g1.users us1 WHERE g1.id = g.id AND us.login = ?1)")
    List<Group> findByUsersIn(String login);

    @Query("select g from Group g JOIN FETCH g.users us WHERE EXISTS " +
            "(select g1 FROM Group g1 JOIN g1.users us1 WHERE g1.id = g.id AND us.login = ?1) order by g.startDate desc")
    List<Group> findByUsersInOrderByStartDateDesc(String login);

    List<Group> findByActiveTrueOrderByStartDateDesc();
}
