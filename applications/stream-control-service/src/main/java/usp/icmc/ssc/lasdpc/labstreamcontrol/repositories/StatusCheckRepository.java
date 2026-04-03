package usp.icmc.ssc.lasdpc.labstreamcontrol.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Atuador;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.StatusCheck;


public interface StatusCheckRepository extends MongoRepository<StatusCheck, String> {

    StatusCheck getByAtuador(Atuador atuador);

    StatusCheck findFirstByAtuadorOrderByDataCadastroDesc(Atuador atuador);

}
