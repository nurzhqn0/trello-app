package kz.bitlab.spring.app.trello_app.repository;

import kz.bitlab.spring.app.trello_app.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Task,Long> {
    List<Task> findByFolderId(Long folderId);

    @Modifying
    @Query("UPDATE Task t SET t.folder = null WHERE t.folder.id = :folderId")
    void setFolderToNullByFolderId(@Param("folderId") Long folderId);
}
