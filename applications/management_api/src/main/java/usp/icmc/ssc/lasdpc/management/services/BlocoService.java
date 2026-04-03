package usp.icmc.ssc.lasdpc.management.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import usp.icmc.ssc.lasdpc.management.entities.Bloco;
import usp.icmc.ssc.lasdpc.management.entities.Instituicao;
import usp.icmc.ssc.lasdpc.management.entities.Piso;
import usp.icmc.ssc.lasdpc.management.exceptions.BusinessException;
import usp.icmc.ssc.lasdpc.management.exceptions.NotFoundRegisterException;
import usp.icmc.ssc.lasdpc.management.repositories.BlocoRespository;
import usp.icmc.ssc.lasdpc.management.utils.Constantes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlocoService {

    @Autowired
    private BlocoRespository blocoRespository;

    @Autowired
    private PisoService pisoService;

    public List<Bloco> list() {
        return this.blocoRespository.findAll();
    }

    public Page<Bloco> getPorInstituicao(Instituicao instituicao, int numPag, int sizePage) {
        Pageable pageable = PageRequest.of(numPag, sizePage, Sort.by("nome").descending());
        return this.blocoRespository.findByInstituicao(instituicao, pageable);
    }

    public List<Piso> getPisos(UUID id) throws NotFoundRegisterException {
        return this.find(id).getPisos();
    }

    public Page<Piso> getPisos(UUID id, int pagNum, int pagSize) throws NotFoundRegisterException {
        return this.pisoService.getPorBloco(this.find(id), pagNum, pagSize);
    }

    public Bloco save(Bloco bloco) throws BusinessException {
        this.validarCamposBloco(bloco);
        return this.blocoRespository.save(bloco);
    }

    public Bloco find(UUID id) throws NotFoundRegisterException {
        Optional<Bloco> bloco = this.blocoRespository.findById(id);

        if (bloco.isEmpty()) {
            throw new NotFoundRegisterException(Constantes.MSG_NAO_ENCONTRADO);
        }

        return bloco.get();
    }

    public void delete(UUID id) throws NotFoundRegisterException {
        Bloco bloco = this.find(id);
        this.blocoRespository.delete(bloco);
    }

    private void validarCamposBloco(Bloco bloco) throws BusinessException {
        if (bloco.getNome().equals("") || bloco.getNome() == null) {
            throw new BusinessException(Constantes.MSG_BLOCO_CAMPO_OBRIGATORIO_NOME);
        }

        if (bloco.getInstituicao().getId().toString().equals("") || bloco.getInstituicao().getId() == null) {
            throw new BusinessException(Constantes.MSG_BLOCO_CAMPO_OBRIGATORIO_iNSTITUICAO);
        }

        // Verifica se o bloco já está cadastrado na instituição.
        Optional<Bloco> blocoPorNome = this.blocoRespository.getFirstByNomeAndInstituicao(
                bloco.getNome(),
                bloco.getInstituicao());

        if (!blocoPorNome.isEmpty() && blocoPorNome.get().getId() != bloco.getId()) {
            throw new BusinessException(Constantes.MSG_BLOCO_NOME_CADASTRADO_INSTITUICAO);
        }
    }
}
