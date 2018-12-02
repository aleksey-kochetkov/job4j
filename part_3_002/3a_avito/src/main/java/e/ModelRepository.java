package e;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ModelRepository extends CrudRepository<Model, Integer> {
    List<Model> findByMarkId(int markId);
}
