package usp.icmc.ssc.lasdpc.management.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import usp.icmc.ssc.lasdpc.management.entities.Bloco;
import usp.icmc.ssc.lasdpc.management.entities.Piso;

import java.util.Optional;
import java.util.UUID;

public interface PisoRepository extends JpaRepository<Piso, UUID> {
    Optional<Piso> getFirstByNomeAndBloco(String nome, Bloco bloco);
    Page<Piso> findByBloco(Bloco bloco, Pageable pageable);
}
