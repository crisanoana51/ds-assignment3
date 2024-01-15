package aprojectDS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import aprojectDS.entities.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<aprojectDS.entities.User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<User> findByFirstName(String firstName);
}
