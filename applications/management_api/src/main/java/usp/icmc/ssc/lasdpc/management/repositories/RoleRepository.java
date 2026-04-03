package usp.icmc.ssc.lasdpc.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import usp.icmc.ssc.lasdpc.management.entities.ERole;
import usp.icmc.ssc.lasdpc.management.entities.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

}
