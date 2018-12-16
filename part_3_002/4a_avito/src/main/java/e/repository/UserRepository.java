package e.repository;

import org.springframework.data.repository.CrudRepository;
import e.domain.User;

public interface UserRepository extends CrudRepository<User, String> {
}
