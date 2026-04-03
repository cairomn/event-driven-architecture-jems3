package usp.icmc.ssc.lasdpc.management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usp.icmc.ssc.lasdpc.management.dtos.TipoAtuadorDTO;
import usp.icmc.ssc.lasdpc.management.entities.Atuador;
import usp.icmc.ssc.lasdpc.management.entities.Microcontrolador;
import usp.icmc.ssc.lasdpc.management.enums.TipoAtuadorEnum;
import usp.icmc.ssc.lasdpc.management.exceptions.BusinessException;
import usp.icmc.ssc.lasdpc.management.exceptions.NotFoundRegisterException;
import usp.icmc.ssc.lasdpc.management.repositories.AtuadorRepository;
import usp.icmc.ssc.lasdpc.management.utils.Constantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AtuadorService {

    @Autowired
    private AtuadorRepository atuadorRepository;

    public List<Atuador> getByMicrocontrolador(Microcontrolador microcontrolador) {
        return this.atuadorRepository.getByMicrocontrolador(microcontrolador);
    }

    public Atuador save(Atuador atuador) throws BusinessException {
        if (atuador.getId() == null) atuador.setStatus(Atuador.ST_ATUADOR_ATIVO);
        this.validarCamposAtuador(atuador);
        return this.atuadorRepository.save(atuador);
    }

    public Atuador find(UUID id) throws NotFoundRegisterException {
        Optional<Atuador> atuador = this.atuadorRepository.findById(id);

        if (atuador.isEmpty()) {
            throw new NotFoundRegisterException(Constantes.MSG_NAO_ENCONTRADO);
        }

        return atuador.get();
    }

    public void delete(UUID id) throws NotFoundRegisterException {
        Atuador atuador = this.find(id);
        this.atuadorRepository.delete(atuador);
    }

    public List<TipoAtuadorDTO> getTiposAtuadores() {
        ArrayList<TipoAtuadorDTO> tiposAtuadoresDTO = new ArrayList<TipoAtuadorDTO>();
        tiposAtuadoresDTO.add(TipoAtuadorDTO.from(TipoAtuadorEnum.TP_ATUADOR_AR_CONDICIONADO));
        return tiposAtuadoresDTO;
    }

    private void validarCamposAtuador(Atuador atuador) throws BusinessException {
        if (atuador.getApelido().equals("") || atuador.getApelido() == null) {
            throw new BusinessException(Constantes.MSG_ATUADOR_CAMPO_OBRIGATORIO_APELIDO);
        }

        if (atuador.getTipoAtuador() < 1) {
            throw new BusinessException(Constantes.MSG_ATUADOR_CAMPO_OBRIGATORIO_TIPO_ATUADOR);
        }

        if (atuador.getMicrocontrolador() == null) {
            throw new BusinessException(Constantes.MSG_ATUADOR_CAMPO_OBRIGATORIO_MICROCONTROLADOR);
        }
    }
}
