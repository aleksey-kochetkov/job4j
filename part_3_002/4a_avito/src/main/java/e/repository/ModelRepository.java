package e.repository;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import e.domain.Model;

public interface ModelRepository extends CrudRepository<Model, Integer> {
    List<Model> findByMarkId(int markId);
}
