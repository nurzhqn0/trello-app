package kz.bitlab.spring.app.trello_app.repository;

import kz.bitlab.spring.app.trello_app.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends JpaRepository<Tasks,Long> {
}
