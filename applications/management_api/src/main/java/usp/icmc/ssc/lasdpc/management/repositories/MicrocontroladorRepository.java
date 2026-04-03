package usp.icmc.ssc.lasdpc.management.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import usp.icmc.ssc.lasdpc.management.entities.Microcontrolador;
import usp.icmc.ssc.lasdpc.management.entities.Sala;

import java.util.List;
import java.util.UUID;

public interface MicrocontroladorRepository extends JpaRepository<Microcontrolador, UUID> {
    List<Microcontrolador> findBySala(Sala sala);
    Page<Microcontrolador> findBySalaAndRemoved(Sala sala, Boolean removed, Pageable pageable);
}
