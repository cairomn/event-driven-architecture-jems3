package usp.icmc.ssc.lasdpc.management.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import usp.icmc.ssc.lasdpc.management.entities.Piso;
import usp.icmc.ssc.lasdpc.management.entities.Sala;

import java.util.Optional;
import java.util.UUID;

public interface SalaRepository extends JpaRepository<Sala, UUID> {
    Optional<Sala> getFirstByNomeAndPiso(String nome, Piso piso);
    Page<Sala> findByPiso(Piso piso, Pageable pageable);
}
