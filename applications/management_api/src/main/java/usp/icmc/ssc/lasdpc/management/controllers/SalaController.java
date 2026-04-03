package usp.icmc.ssc.lasdpc.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import usp.icmc.ssc.lasdpc.management.controllers.responses.PagedResponse;
import usp.icmc.ssc.lasdpc.management.controllers.responses.RestResponse;
import usp.icmc.ssc.lasdpc.management.dtos.SalaDTO;
import usp.icmc.ssc.lasdpc.management.entities.Microcontrolador;
import usp.icmc.ssc.lasdpc.management.entities.Sala;
import usp.icmc.ssc.lasdpc.management.exceptions.BusinessException;
import usp.icmc.ssc.lasdpc.management.exceptions.NotFoundRegisterException;
import usp.icmc.ssc.lasdpc.management.services.SalaService;
import usp.icmc.ssc.lasdpc.management.utils.Constantes;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/salas")
public class SalaController extends AbstractController {

    @Autowired
    private SalaService salaService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<SalaDTO> getById(@PathVariable("id") UUID id) {
        try {
            Sala sala = this.salaService.find(id);
            return new RestResponse<>(HttpStatus.OK, Constantes.HTTP_CODE_OK, Constantes.MSG_RESP_CONSULTA,
                    SalaDTO.from(sala));
        } catch (NotFoundRegisterException e) {
            return new RestResponse<>(HttpStatus.UNPROCESSABLE_ENTITY, Constantes.HTTP_CODE_UNPROCESSABLE_ENTITY,
                    e.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<SalaDTO> create(@RequestBody Sala sala) {
        try {
            Sala save = this.salaService.save(sala);
            return new RestResponse<>(HttpStatus.CREATED, Constantes.HTTP_CODE_CREATED, Constantes.MSG_RESP_CADASTRO,
                    SalaDTO.from(save));
        } catch (BusinessException e) {
            return new RestResponse<>(HttpStatus.UNPROCESSABLE_ENTITY, Constantes.HTTP_CODE_UNPROCESSABLE_ENTITY,
                    e.getMessage());
        }

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<SalaDTO> update(
            @RequestBody Sala sala,
            @PathVariable("id") final UUID id) {
        try {
            Sala updated = this.salaService.save(sala);
            return new RestResponse<>(HttpStatus.OK, Constantes.HTTP_CODE_OK, Constantes.MSG_RESP_ALTERACAO,
                    SalaDTO.from(updated));
        } catch (BusinessException e) {
            return new RestResponse<>(HttpStatus.UNPROCESSABLE_ENTITY, Constantes.HTTP_CODE_UNPROCESSABLE_ENTITY,
                    e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<SalaDTO> delete(
            @PathVariable("id") final UUID id) {
        try {
            this.salaService.delete(id);
            return new RestResponse<>(HttpStatus.NO_CONTENT, Constantes.HTTP_CODE_NO_CONTENT,
                    Constantes.MSG_RESP_REMOVIDO);
        } catch (NotFoundRegisterException e) {
            return new RestResponse<>(HttpStatus.NOT_FOUND, Constantes.HTTP_CODE_NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}/microcontroladores")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_VIEWER')")
    public RestResponse<List<Microcontrolador>> getMicrocontroladores(@PathVariable("id") UUID id) {
        try {
            List<Microcontrolador> microcontroladores = this.salaService.getMicrocontroladores(id);
            return new RestResponse<>(HttpStatus.OK, Constantes.HTTP_CODE_OK, Constantes.MSG_RESP_CONSULTA, microcontroladores);
        } catch(NotFoundRegisterException e) {
            return new RestResponse<>(HttpStatus.NOT_FOUND, Constantes.HTTP_CODE_NOT_FOUND, Constantes.MSG_NAO_ENCONTRADO);
        }
    }

    @GetMapping("/{id}/microcontroladores/paginados")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_VIEWER')")
    public PagedResponse<Microcontrolador> getMicrocontroladores(
            @PathVariable("id") UUID id,
            @RequestParam(name = "pagNum", defaultValue = Constantes.NUMERO_PAGINA_PADRAO, required = false) int pagNum,
            @RequestParam(name = "pagSize", defaultValue = Constantes.TAMANHO_PAGINA_PADRAO, required = false) int pagSize) {
        try {
            Page<Microcontrolador> page = this.salaService.getMicrocontroladoresPaginados(id, pagNum, pagSize);
            PagedResponse<Microcontrolador> response = new PagedResponse<>();
            response.setContent(page.getContent());
            response.setLast(page.isLast());
            response.setPage(page.getNumber());
            response.setSize(page.getSize());
            response.setTotalPages(page.getTotalPages());
            response.setTotalElements(page.getTotalElements());
            return response;
        } catch (NotFoundRegisterException e) {
            return new PagedResponse<>();
        }
    }
}
