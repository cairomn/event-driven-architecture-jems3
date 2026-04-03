package usp.icmc.ssc.lasdpc.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import usp.icmc.ssc.lasdpc.management.entities.Microcontrolador;
import usp.icmc.ssc.lasdpc.management.entities.Sensor;

import java.util.List;
import java.util.UUID;

public interface SensorRepository extends JpaRepository<Sensor, UUID> {
    Sensor getById(UUID id);
    List<Sensor> getByMicrocontrolador(Microcontrolador microcontrolador);
}
