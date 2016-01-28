package ua.com.jon.common.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.com.jon.common.domain.Sprint;
import ua.com.jon.common.domain.SprintType;
import ua.com.jon.common.domain.User;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/29/13
 */
public interface SprintRepository extends CrudRepository<Sprint, Long> {

    @Query("select s from Sprint s where s.name = :sprintName")
    Sprint findByName(@Param("sprintName") String sprintName);

    @Query("select s from Sprint s where s.name in (:names)")
    List<Sprint> findByNames(@Param("names") Collection<String> names);

    @Query("select s from Sprint s where s.id in (:ids)")
    List<Sprint> findByIds(@Param("ids") Collection<Long> ids);


    @Query("select s from Sprint s where s.type = (:type)")
    List<Sprint> findByType(@Param("type") SprintType type);

    @Query("select distinct s from Sprint s JOIN FETCH s.tasks tmpls JOIN FETCH tmpls.tasks tss where EXISTS" +
            "(select s1 FROM Sprint s1 JOIN s1.tasks ts1 WHERE s1.id = s.id AND tss.user.login = :userName " +
                "AND tss.group.id = :groupId)")
//    @Query("select g from GroupDTO g JOIN FETCH g.users us WHERE EXISTS " +
//            "(select g1 FROM GroupDTO g1 JOIN g1.users us1 WHERE g1.id = g.id AND us.login = ?1)")
    List<Sprint> findByUserAndGroup(@Param("userName") String userName, @Param("groupId") Long groupId);
}
