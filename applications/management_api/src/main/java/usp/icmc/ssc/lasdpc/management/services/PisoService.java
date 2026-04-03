package usp.icmc.ssc.lasdpc.management.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import usp.icmc.ssc.lasdpc.management.entities.Bloco;
import usp.icmc.ssc.lasdpc.management.entities.Piso;
import usp.icmc.ssc.lasdpc.management.entities.Sala;
import usp.icmc.ssc.lasdpc.management.exceptions.BusinessException;
import usp.icmc.ssc.lasdpc.management.exceptions.NotFoundRegisterException;
import usp.icmc.ssc.lasdpc.management.repositories.PisoRepository;
import usp.icmc.ssc.lasdpc.management.utils.Constantes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PisoService {

    @Autowired
    private PisoRepository pisoRepository;

    @Autowired
    private SalaService salaService;

    public Piso save(Piso piso) throws BusinessException {
        this.validarCamposPiso(piso);
        return this.pisoRepository.save(piso);
    }

    public Piso find(UUID id) throws NotFoundRegisterException {
        Optional<Piso> piso = this.pisoRepository.findById(id);

        if (piso.isEmpty()) {
            throw new NotFoundRegisterException(Constantes.MSG_NAO_ENCONTRADO);
        }

        return piso.get();
    }

    public void delete(UUID id) throws NotFoundRegisterException {
        Piso piso = this.find(id);
        this.pisoRepository.delete(piso);
    }

    public Page<Piso> getPorBloco(Bloco bloco, int numPag, int sizePage) {
        PageRequest pageable = PageRequest.of(numPag, sizePage, Sort.by("nome").descending());
        return this.pisoRepository.findByBloco(bloco, pageable);
    }

    public List<Sala> getSalasPorPiso(UUID id) throws NotFoundRegisterException {
        return this.pisoRepository.findById(id).get().getSalas();
    }

    public Page<Sala> getSalasPorPiso(UUID id, int numPag, int sizePage) throws NotFoundRegisterException {
        return this.salaService.getSalasPorPiso(this.find(id), numPag, sizePage);
    }

    private void validarCamposPiso(Piso piso) throws BusinessException {
        if (piso.getNome().equals("") || piso.getNome() == null) {
            throw new BusinessException(Constantes.MSG_PISO_CAMPO_OBRIGATORIO_NOME);
        }

        if (piso.getBloco().getId().toString().equals("") || piso.getBloco().getId() == null) {
            throw new BusinessException(Constantes.MSG_PISO_CAMPO_OBRIGATORIO_BLOCO);
        }

        // Verifica se o piso já está cadastrado no bloco.
        Optional<Piso> pisoPorNome = this.pisoRepository.getFirstByNomeAndBloco(piso.getNome(), piso.getBloco());
        if (!pisoPorNome.isEmpty()) {
            if (pisoPorNome.get().getId() != piso.getId()) {
                throw new BusinessException(Constantes.MSG_PISO_CAMPO_OBRIGATORIO_NOME_CADASTRADO_BLOCO);
            }
        }
    }
}
