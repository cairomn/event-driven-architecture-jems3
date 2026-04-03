package usp.icmc.ssc.lasdpc.management.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import usp.icmc.ssc.lasdpc.management.entities.Microcontrolador;
import usp.icmc.ssc.lasdpc.management.entities.Piso;
import usp.icmc.ssc.lasdpc.management.entities.Sala;
import usp.icmc.ssc.lasdpc.management.exceptions.BusinessException;
import usp.icmc.ssc.lasdpc.management.exceptions.NotFoundRegisterException;
import usp.icmc.ssc.lasdpc.management.repositories.SalaRepository;
import usp.icmc.ssc.lasdpc.management.utils.Constantes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private MicrocontroladorService microcontroladorService;

    public Sala save(Sala sala) throws BusinessException {
        this.validarCamposSala(sala);
        return this.salaRepository.save(sala);
    }

    public Sala find(UUID id) throws NotFoundRegisterException {
        Optional<Sala> sala = this.salaRepository.findById(id);
        if (sala.isEmpty()) throw new NotFoundRegisterException(Constantes.MSG_NAO_ENCONTRADO);
        return sala.get();
    }

    public void delete(UUID id) throws NotFoundRegisterException {
        Sala sala = this.find(id);
        this.salaRepository.delete(sala);
    }

    public List<Microcontrolador> getMicrocontroladores(UUID id) throws NotFoundRegisterException {
        return this.find(id).getMicrocontroladores();
    }

    public Page<Microcontrolador> getMicrocontroladoresPaginados(UUID id, int pagNum, int pagSize) throws NotFoundRegisterException {
        return this.microcontroladorService.getPorSalaPaginada(this.find(id), pagNum, pagSize);
    }

    public Page<Sala> getSalasPorPiso(Piso piso, int numPag, int sizePage) {
        PageRequest pageable = PageRequest.of(numPag, sizePage, Sort.by("nome").descending());
        return this.salaRepository.findByPiso(piso, pageable);
    }

    private void validarCamposSala(Sala sala) throws BusinessException {
        if (sala.getNome().equals("") || sala.getNome() == null) {
            throw new BusinessException(Constantes.MSG_SALA_CAMPO_OBRIGATORIO_NOME);
        }

        if (sala.getPiso().getId().toString().equals("") || sala.getPiso().getId() == null) {
            throw new BusinessException(Constantes.MSG_SALA_CAMPO_OBRIGATORIO_PISO);
        }

        // Tenho que verificar o bloco e a instituicao
        Optional<Sala> salaOptional = this.salaRepository.getFirstByNomeAndPiso(sala.getNome(), sala.getPiso());
        if (!salaOptional.isEmpty()) {
            if (salaOptional.get().getId() != sala.getId()) {
                throw new BusinessException(Constantes.MSG_SALA_CAMPO_OBRIGATORIO_NOME_CADASTRADO_PISO);
            }
        }
    }
}
