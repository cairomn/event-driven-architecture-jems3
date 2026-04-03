package usp.icmc.ssc.lasdpc.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import usp.icmc.ssc.lasdpc.management.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNome(String nome);

    Optional<User> findByEmail(String email);

    Boolean existsByNome(String nome);

    Boolean existsByEmail(String email);
}
