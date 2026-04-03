package usp.icmc.ssc.lasdpc.labstreamcontrol.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Microcontrolador;

public interface MicrocontroladoRepository extends MongoRepository<Microcontrolador, String> {

    Microcontrolador getByMicrocontroladorID(UUID microcontroladorID);

}
