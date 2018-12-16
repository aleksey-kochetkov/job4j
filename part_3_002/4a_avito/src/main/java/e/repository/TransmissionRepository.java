package e.repository;

import org.springframework.data.repository.CrudRepository;
import e.domain.Transmission;

public interface TransmissionRepository extends CrudRepository<Transmission, Integer> {
}
