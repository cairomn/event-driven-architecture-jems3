package usp.icmc.ssc.lasdpc.labstreamcontrol.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Atuador;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Medicao;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Sensor;
import usp.icmc.ssc.lasdpc.labstreamcontrol.repositories.MedicaoRepository;

@Service
public class MedicaoService {

    private final MedicaoRepository medicaoRepository;

    public MedicaoService(MedicaoRepository medicaoRepository) {
        this.medicaoRepository = medicaoRepository;
    }

    public Medicao salvar(Medicao medicao) {
        return this.medicaoRepository.save(medicao);
    }

    public Medicao getPorId(String id) throws IllegalAccessException {
        return this.medicaoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
    }

    public List<Medicao> getPorAtuador(Atuador atuador) {
        return this.medicaoRepository.getByAtuador(atuador, Sort.by("dataCadastro").descending());
    }

    public List<Medicao> getPorSensor(Sensor sensor) {
        return this.medicaoRepository.getBySensor(sensor, Sort.by("dataCadastro").descending());
    }

    public Page<Medicao> getPorAtuador(Atuador atuador, int numPag, int sizePage) {
        PageRequest pageable = PageRequest.of(numPag, sizePage, Sort.by("dataCadastro").descending());
        return this.medicaoRepository.getByAtuador(atuador, pageable);
    }

    public Page<Medicao> getPorSensor(Sensor sensor, int numPag, int sizePage) {
        PageRequest pageable = PageRequest.of(numPag, sizePage, Sort.by("dataCadastro").descending());
        return this.medicaoRepository.getBySensor(sensor, pageable);
    }
}
