package usp.icmc.ssc.lasdpc.labstreamcontrol.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Atuador;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Microcontrolador;

import java.util.List;
import java.util.UUID;

public interface AtuadorRepository extends MongoRepository<Atuador, String> {
    List<Atuador> getByMicrocontrolador(Microcontrolador microcontrolador);
    Atuador findByAtuadorID(UUID atuadorID);
}
