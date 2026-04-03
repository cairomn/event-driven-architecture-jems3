package usp.icmc.ssc.lasdpc.labstreamcontrol.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Microcontrolador;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Sensor;

import java.util.List;
import java.util.UUID;

public interface SensorRepository extends MongoRepository<Sensor, String> {
    List<Sensor> getByMicrocontrolador(Microcontrolador microcontrolador);

    @Query(value="{ sensorID : ?0 }")
    Sensor getBySensorID(UUID sensorID);
}
