package e.repository;

import org.springframework.data.repository.CrudRepository;
import e.domain.Mark;

public interface MarkRepository extends CrudRepository<Mark, Integer> {
}
