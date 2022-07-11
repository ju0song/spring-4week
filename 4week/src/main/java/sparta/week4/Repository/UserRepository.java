package sparta.week4.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sparta.week4.Entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
