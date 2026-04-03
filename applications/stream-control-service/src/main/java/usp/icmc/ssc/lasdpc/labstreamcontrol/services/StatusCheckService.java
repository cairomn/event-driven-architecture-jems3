package usp.icmc.ssc.lasdpc.labstreamcontrol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Atuador;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.StatusCheck;
import usp.icmc.ssc.lasdpc.labstreamcontrol.repositories.StatusCheckRepository;

import java.util.UUID;

@Service
public class StatusCheckService {

    private final StatusCheckRepository statusCheckRepository;

    private StatusCheckService(StatusCheckRepository statusCheckRepository) {
        this.statusCheckRepository = statusCheckRepository;
    }

    public StatusCheck salvar(StatusCheck statusCheck) {
        return this.statusCheckRepository.save(statusCheck);
    }

    public StatusCheck getPorAtuadorID(Atuador atuador)  {
        return this.statusCheckRepository.findFirstByAtuadorOrderByDataCadastroDesc(atuador);
    }
}
