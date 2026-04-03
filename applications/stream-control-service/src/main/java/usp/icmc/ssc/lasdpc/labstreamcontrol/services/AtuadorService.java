package usp.icmc.ssc.lasdpc.labstreamcontrol.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Atuador;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Medicao;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Microcontrolador;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.StatusCheck;
import usp.icmc.ssc.lasdpc.labstreamcontrol.repositories.AtuadorRepository;

@Service
public class AtuadorService {

    @Autowired
    private AtuadorRepository atuadorRepository;

    @Autowired
    private MedicaoService medicaoService;

    @Autowired
    private StatusCheckService statusCheckService;

    public Atuador salvar(Atuador atuador) {
        return this.atuadorRepository.save(atuador);
    }

    public Atuador getPorId(String id) throws IllegalAccessException {
        return this.atuadorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
    }

    public Atuador getPorAtuadorID(UUID atuadorID) {
        return this.atuadorRepository.findByAtuadorID(atuadorID);
    }

    public List<Atuador> getAtuadoresPorMicrocontrolador(Microcontrolador microcontrolador) {
        return this.atuadorRepository.getByMicrocontrolador(microcontrolador);
    }

    public Page<Medicao> getMediacoes(UUID atuadorID, int pagNum, int pagSize) {
        return this.medicaoService.getPorAtuador(this.atuadorRepository.findByAtuadorID(atuadorID), pagNum, pagSize);
    }

    public List<StatusCheck> getStatus(List<UUID> ids) {
        ArrayList<StatusCheck> status = new ArrayList<>();
        for (UUID id: ids) {
            StatusCheck statusCheck = this.statusCheckService.getPorAtuadorID(this.getPorAtuadorID(id));
            statusCheck.setTemp(21);
            status.add(statusCheck);
        }
        return status;
    }
}
