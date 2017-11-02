package pro.budthapa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.budthapa.domain.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long>{
	Optional<ToDo> findById(Long id);
}
