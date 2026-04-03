package usp.icmc.ssc.lasdpc.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import usp.icmc.ssc.lasdpc.management.entities.Atuador;
import usp.icmc.ssc.lasdpc.management.entities.Microcontrolador;

import java.util.List;
import java.util.UUID;

public interface AtuadorRepository extends JpaRepository<Atuador, UUID> {
    Atuador getById(UUID id);
    List<Atuador> getByMicrocontrolador(Microcontrolador microcontrolador);
}
