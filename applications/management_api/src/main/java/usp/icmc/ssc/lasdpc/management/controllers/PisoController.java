package usp.icmc.ssc.lasdpc.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import usp.icmc.ssc.lasdpc.management.controllers.responses.PagedResponse;
import usp.icmc.ssc.lasdpc.management.controllers.responses.RestResponse;
import usp.icmc.ssc.lasdpc.management.dtos.PisoDTO;
import usp.icmc.ssc.lasdpc.management.entities.Piso;
import usp.icmc.ssc.lasdpc.management.entities.Sala;
import usp.icmc.ssc.lasdpc.management.exceptions.BusinessException;
import usp.icmc.ssc.lasdpc.management.exceptions.NotFoundRegisterException;
import usp.icmc.ssc.lasdpc.management.services.PisoService;
import usp.icmc.ssc.lasdpc.management.utils.Constantes;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/pisos")
public class PisoController extends AbstractController {

    @Autowired
    private PisoService pisoService;

    @GetMapping("/{id}/salas")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_VIEWER')")
    public RestResponse<List<Sala>> getSalasPorPiso(@PathVariable("id") final UUID id) {
        try {
            List<Sala> salas = this.pisoService.getSalasPorPiso(id);
            return new RestResponse<>(HttpStatus.OK, Constantes.HTTP_CODE_OK, Constantes.MSG_RESP_CONSULTA, salas);
        } catch (NotFoundRegisterException e) {
            return new RestResponse<>(HttpStatus.NOT_FOUND, Constantes.HTTP_CODE_NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}/salas/paginadas")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_VIEWER')")
    public PagedResponse<Sala> getSalasPorPiso(
            @PathVariable("id") final UUID id,
            @RequestParam(name = "pagNum", defaultValue = Constantes.NUMERO_PAGINA_PADRAO, required = false) int pagNum,
            @RequestParam(name = "pagSize", defaultValue = Constantes.TAMANHO_PAGINA_PADRAO, required = false) int pagSize) {
        try {
            Page<Sala> page = this.pisoService.getSalasPorPiso(id, pagNum, pagSize);
            PagedResponse<Sala> response = new PagedResponse<>();
            response.setContent(page.getContent());
            response.setLast(page.isLast());
            response.setPage(page.getNumber());
            response.setSize(page.getSize());
            response.setTotalPages(page.getTotalPages());
            response.setTotalElements(page.getTotalElements());
            return response;
        } catch (NotFoundRegisterException e) {
            PagedResponse<Sala> response = new PagedResponse<>();
            return response;
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<PisoDTO> create(@RequestBody Piso piso) {
        try {
            Piso save = this.pisoService.save(piso);
            return new RestResponse<>(HttpStatus.CREATED, Constantes.HTTP_CODE_CREATED, Constantes.MSG_RESP_CADASTRO,
                    PisoDTO.from(save));
        } catch (BusinessException e) {
            return new RestResponse<>(HttpStatus.UNPROCESSABLE_ENTITY, Constantes.HTTP_CODE_UNPROCESSABLE_ENTITY,
                    e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<PisoDTO> getById(
            @PathVariable("id") final UUID id) {
        try {
            Piso piso = this.pisoService.find(id);
            return new RestResponse<>(HttpStatus.OK, Constantes.HTTP_CODE_OK, Constantes.MSG_RESP_CONSULTA,
                    PisoDTO.from(piso));
        } catch (NotFoundRegisterException e) {
            return new RestResponse<>(HttpStatus.UNPROCESSABLE_ENTITY, Constantes.HTTP_CODE_UNPROCESSABLE_ENTITY,
                    e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<PisoDTO> update(
            @RequestBody Piso piso,
            @PathVariable("id") final UUID id) {
        try {
            Piso updated = this.pisoService.save(piso);
            return new RestResponse<>(HttpStatus.OK, Constantes.HTTP_CODE_OK, Constantes.MSG_RESP_ALTERACAO,
                    PisoDTO.from(updated));
        } catch (BusinessException e) {
            return new RestResponse<>(HttpStatus.UNPROCESSABLE_ENTITY, Constantes.HTTP_CODE_UNPROCESSABLE_ENTITY,
                    e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<PisoDTO> delete(
            @PathVariable("id") final UUID id) {
        try {
            this.pisoService.delete(id);
            return new RestResponse<>(HttpStatus.NO_CONTENT, Constantes.HTTP_CODE_NO_CONTENT,
                    Constantes.MSG_RESP_REMOVIDO);
        } catch (NotFoundRegisterException e) {
            return new RestResponse<>(HttpStatus.NOT_FOUND, Constantes.HTTP_CODE_NOT_FOUND, e.getMessage());
        }
    }

}
