package e.repository;

import org.springframework.data.repository.CrudRepository;
import e.domain.Engine;

public interface EngineRepository extends CrudRepository<Engine, Integer> {
}
