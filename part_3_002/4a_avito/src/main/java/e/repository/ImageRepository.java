package e.repository;

import org.springframework.data.repository.CrudRepository;
import e.domain.Image;

public interface ImageRepository extends CrudRepository<Image, Integer> {
}
