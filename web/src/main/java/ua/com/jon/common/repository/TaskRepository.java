package ua.com.jon.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.jon.common.domain.Task;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/23/13
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select t from ua.com.jon.common.domain.Task t where t.sprint.name = :sprintName")
    List<Task> findBySprintName(@Param("sprintName") String sprintName);

    @Query("select t from ua.com.jon.common.domain.Task t where t.user.login = :userName")
    List<Task> findByUserName(@Param("userName") String userName);

    @Query("select t from ua.com.jon.common.domain.Task t JOIN t.user.groups gs where gs.id = :groupId and t.result <> ''")
    List<Task> findEvaluatedByGroupId(@Param("groupId") Long groupId);

    //@Query("select t from ua.com.jon.common.domain.Task t where t.user.login = :userName and t.sprint.name = :sprintName")
    @Query("select t from ua.com.jon.common.domain.Task t JOIN FETCH t.taskTemplate JOIN FETCH t.group where t.user.login = :userName and t.sprint.id = :sprintId")
    List<Task> findByUserAndSprint(@Param("userName") String userName, @Param("sprintId") Long sprintId);

    @Query("select t from ua.com.jon.common.domain.Task t JOIN FETCH t.taskTemplate JOIN FETCH t.group JOIN FETCH t.user us JOIN FETCH us.groups gs where EXISTS " +
            "(select t1 FROM ua.com.jon.common.domain.Task t1 JOIN us.groups gs1 WHERE t1.id = t.id AND gs.id = :groupId " +
            "AND t.user.id = :userId and t.sprint.id = :sprintId) AND t.group.id = :groupId")
    List<Task> findByUserAndSprintAndGroup(@Param("userId") Long userId, @Param("sprintId") Long sprintId, @Param("groupId") Long groupId);

    @Query("select t from ua.com.jon.common.domain.Task t JOIN FETCH t.taskTemplate JOIN FETCH t.group JOIN FETCH t.user us JOIN FETCH us.groups gs where EXISTS " +
            "(select t1 FROM ua.com.jon.common.domain.Task t1 JOIN us.groups gs1 WHERE t1.id = t.id AND gs.id = :groupId " +
            "AND t1.user.id = :userId) AND t.group.id = :groupId order by t.id desc")
    List<Task> findByUserAndGroup(@Param("userId") Long userId, @Param("groupId") Long groupId);

    @Query("select distinct t from ua.com.jon.common.domain.Task t JOIN t.user.groups gs JOIN FETCH t.group where gs.id = :groupId and t.taskTemplate.id = :templateId and t.result <> ''")
    List<Task> findEvaluatedByGroupIdAndTaskId(@Param("groupId") Long groupId, @Param("templateId")Long templateId);

    @Query("select distinct t from ua.com.jon.common.domain.Task t JOIN t.user.groups gs JOIN FETCH t.group where " +
            "gs.id = :groupId and t.taskTemplate.id = :templateId and t.sprint.id = :sprintId")
    List<Task> findByGroupIdAndSprintIdAndTaskId(@Param("groupId") Long groupId, @Param("sprintId") Long sprintId, @Param("templateId") Long templateId);

    @Query("select t from ua.com.jon.common.domain.Task t JOIN t.group gs where gs.id = :groupId")
    List<Task> findByGroupId(@Param("groupId") Long groupId);

    @Query("select t from ua.com.jon.common.domain.Task t JOIN t.group gs JOIN t.user u where gs.id = :groupId and u.ignore = false")
    List<Task> findByGroupIdAndUserNotIgnore(@Param("groupId") Long groupId);

    @Query("select t from ua.com.jon.common.domain.Task t JOIN t.group gs where t.user.login = :userName and gs.id = :groupId")
    List<Task> findByUserIdAndGroupId(@Param("groupId") String userName, @Param("groupId") Long groupId);

    @Query(value = "select t.user.id from TASKS t", nativeQuery = true)
    List<Object> findUserIds();
}
