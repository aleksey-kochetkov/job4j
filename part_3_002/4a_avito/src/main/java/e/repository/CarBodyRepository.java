package e.repository;

import org.springframework.data.repository.CrudRepository;
import e.domain.CarBody;

public interface CarBodyRepository extends CrudRepository<CarBody, Integer> {
}
