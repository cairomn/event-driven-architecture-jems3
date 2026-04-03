package usp.icmc.ssc.lasdpc.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import usp.icmc.ssc.lasdpc.management.controllers.responses.RestResponse;
import usp.icmc.ssc.lasdpc.management.dtos.TipoAtuadorDTO;
import usp.icmc.ssc.lasdpc.management.services.AtuadorService;
import usp.icmc.ssc.lasdpc.management.utils.Constantes;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipos-atuadores")
public class TipoAtuadorController extends AbstractController {

    @Autowired
    private AtuadorService atuadorService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_VIEWER')")
    public RestResponse<List<TipoAtuadorDTO>> getTiposAtuadores() {
        return new RestResponse<>(HttpStatus.OK, Constantes.HTTP_CODE_OK, Constantes.MSG_RESP_CONSULTA,
                this.atuadorService.getTiposAtuadores());
    }

}
