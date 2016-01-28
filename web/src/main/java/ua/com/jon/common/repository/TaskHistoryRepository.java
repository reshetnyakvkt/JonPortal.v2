package ua.com.jon.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.jon.common.domain.TaskHistory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 31.05.14
 */
public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Long> {
//    @Query("select t from TaskHistory t where t.hash = :hash")
    TaskHistory findByHash(@Param("hash") String hash);
}
