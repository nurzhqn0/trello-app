package kz.bitlab.spring.app.trello_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoldersRepository extends JpaRepository<kz.bitlab.spring.app.trello_app.model.Folders,Long> {

}
