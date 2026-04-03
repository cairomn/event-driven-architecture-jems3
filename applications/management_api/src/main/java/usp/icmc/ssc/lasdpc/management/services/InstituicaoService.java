package usp.icmc.ssc.lasdpc.management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import usp.icmc.ssc.lasdpc.management.entities.Bloco;
import usp.icmc.ssc.lasdpc.management.entities.Instituicao;
import usp.icmc.ssc.lasdpc.management.exceptions.BusinessException;
import usp.icmc.ssc.lasdpc.management.exceptions.NotFoundRegisterException;
import usp.icmc.ssc.lasdpc.management.repositories.InsituticaoRepository;
import usp.icmc.ssc.lasdpc.management.utils.Constantes;
import usp.icmc.ssc.lasdpc.management.utils.ValidacaoUtil;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InstituicaoService {

    @Autowired
    private InsituticaoRepository insituticaoRepository;

    @Autowired
    private BlocoService blocoService;

    public Page<Instituicao> all(int numPag, int sizePage) {
        Pageable pageable = PageRequest.of(numPag, sizePage, Sort.by("nome").descending());
        return this.insituticaoRepository.findAll(pageable);
    }

    public List<Bloco> getBlocos(UUID id) throws NotFoundRegisterException {
        return this.find(id).getBlocos();
    }

    public Page<Bloco> getBlocos(UUID id, int numPag, int sizePage)
            throws NotFoundRegisterException {
        return this.blocoService.getPorInstituicao(this.find(id), numPag, sizePage);
    }

    public Instituicao save(Instituicao instituicao) throws BusinessException {
        this.validarCamposInstituicao(instituicao);
        return this.insituticaoRepository.save(instituicao);
    }

    public Instituicao find(UUID id) throws NotFoundRegisterException {
        Optional<Instituicao> instituicao = this.insituticaoRepository.findById(id);

        if (instituicao.isEmpty()) {
            throw new NotFoundRegisterException(Constantes.MSG_NAO_ENCONTRADO);
        }

        return instituicao.get();
    }

    public void delete(UUID id) throws NotFoundRegisterException {
        Instituicao instituicao = this.find(id);
        this.insituticaoRepository.delete(instituicao);
    }

    private void validarCamposInstituicao(Instituicao instituicao) throws BusinessException {
        if (instituicao.getNome().equals("") || instituicao.getNome() == null) {
            throw new BusinessException(Constantes.MSG_INSTITUICAO_CAMPO_OBRIGATORIO_NOME);
        }

        if (instituicao.getCnpj().equals("") || instituicao.getCnpj() == null) {
            throw new BusinessException(Constantes.MSG_INSTITUICAO_CAMPO_OBRIGATORIO_CNPJ);
        }

        if (!ValidacaoUtil.isCNPJ(instituicao.getCnpj())) {
            throw new BusinessException(Constantes.MSG_INSTITUICAO_CAMPO_VALIDO_CNPJ);
        }

        Optional<Instituicao> instValidacao = this.insituticaoRepository.getByNome(instituicao.getNome());
        if (!instValidacao.isEmpty() && instValidacao.get().getId() != instituicao.getId()) {
            throw new BusinessException(Constantes.MSG_INSTITUICAO_NOME_CADASTRADO_INSTITUICAO);
        }
    }
}
