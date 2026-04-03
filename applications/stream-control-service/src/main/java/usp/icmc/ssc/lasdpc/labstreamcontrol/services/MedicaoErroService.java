package usp.icmc.ssc.lasdpc.labstreamcontrol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.MedicaoErro;
import usp.icmc.ssc.lasdpc.labstreamcontrol.repositories.MedicaoErroRepository;

@Service
public class MedicaoErroService {
    
    @Autowired
    private MedicaoErroRepository medicaoErroRepository;

    public MedicaoErro salvar(String msg) {
        MedicaoErro medicaoErro = new MedicaoErro();
        medicaoErro.setDescricao(msg);
        return this.medicaoErroRepository.save(medicaoErro);
    }
}
