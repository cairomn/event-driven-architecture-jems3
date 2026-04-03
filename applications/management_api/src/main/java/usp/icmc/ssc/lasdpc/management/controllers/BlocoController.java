package usp.icmc.ssc.lasdpc.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import usp.icmc.ssc.lasdpc.management.controllers.responses.PagedResponse;
import usp.icmc.ssc.lasdpc.management.controllers.responses.RestResponse;
import usp.icmc.ssc.lasdpc.management.dtos.BlocoDTO;
import usp.icmc.ssc.lasdpc.management.entities.Bloco;
import usp.icmc.ssc.lasdpc.management.entities.Piso;
import usp.icmc.ssc.lasdpc.management.exceptions.BusinessException;
import usp.icmc.ssc.lasdpc.management.exceptions.NotFoundRegisterException;
import usp.icmc.ssc.lasdpc.management.services.BlocoService;
import usp.icmc.ssc.lasdpc.management.utils.Constantes;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/blocos")
public class BlocoController extends AbstractController {

    @Autowired
    private BlocoService blocoService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<BlocoDTO> getById(@PathVariable("id") final UUID id) {
        try {
            Bloco bloco = this.blocoService.find(id);
            return new RestResponse<BlocoDTO>(HttpStatus.OK, Constantes.HTTP_CODE_OK, Constantes.MSG_RESP_CONSULTA,
                    BlocoDTO.from(bloco));
        } catch (NotFoundRegisterException e) {
            return new RestResponse<BlocoDTO>(HttpStatus.NOT_FOUND, Constantes.HTTP_CODE_NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}/pisos")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_VIEWER')")
    public RestResponse<List<Piso>> getPisos(@PathVariable("id") final UUID id) throws NotFoundRegisterException {
        try {
            return new RestResponse<>(HttpStatus.OK, Constantes.HTTP_CODE_OK, Constantes.MSG_RESP_CONSULTA, this.blocoService.getPisos(id));
        } catch (NotFoundRegisterException e) {
            return new RestResponse<>(HttpStatus.NOT_FOUND, Constantes.HTTP_CODE_NOT_FOUND, Constantes.MSG_NAO_ENCONTRADO);
        }
    }

    @GetMapping("/{id}/pisos/paginados")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_VIEWER')")
    public PagedResponse<Piso> getPisos(
        @PathVariable("id") final UUID id,
        @RequestParam(name = "pagNum", defaultValue = Constantes.NUMERO_PAGINA_PADRAO, required = false) int pagNum,
        @RequestParam(name = "pagSize", defaultValue = Constantes.TAMANHO_PAGINA_PADRAO, required = false) int pagSize
    ) {
        try {
            Page<Piso> page = this.blocoService.getPisos(id, pagNum, pagSize);

            PagedResponse<Piso> response = new PagedResponse<>();
            response.setContent(page.getContent());
            response.setLast(page.isLast());
            response.setPage(page.getNumber());
            response.setSize(page.getSize());
            response.setTotalPages(page.getTotalPages());
            response.setTotalElements(page.getTotalElements());
            return response;
        } catch (NotFoundRegisterException e) {
            PagedResponse<Piso> response = new PagedResponse<>();
            return response;
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<BlocoDTO> create(@RequestBody Bloco bloco) throws BusinessException {
        try {
            Bloco save = this.blocoService.save(bloco);
            return new RestResponse<BlocoDTO>(HttpStatus.CREATED, Constantes.HTTP_CODE_CREATED,
                    Constantes.MSG_RESP_CADASTRO, BlocoDTO.from(save));
        } catch (BusinessException e) {
            return new RestResponse<BlocoDTO>(HttpStatus.UNPROCESSABLE_ENTITY,
                    Constantes.HTTP_CODE_UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<BlocoDTO> update(
            @RequestBody Bloco bloco,
            @PathVariable("id") UUID id) throws BusinessException {
        try {
            Bloco updated = this.blocoService.save(bloco);
            return new RestResponse<BlocoDTO>(HttpStatus.OK, Constantes.HTTP_CODE_OK, Constantes.MSG_RESP_ALTERACAO,
                    BlocoDTO.from(updated));
        } catch (BusinessException e) {
            return new RestResponse<BlocoDTO>(HttpStatus.UNPROCESSABLE_ENTITY,
                    Constantes.HTTP_CODE_UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<BlocoDTO> delete(@PathVariable("id") UUID id) throws NotFoundRegisterException {
        try {
            this.blocoService.delete(id);
            return new RestResponse<BlocoDTO>(HttpStatus.NO_CONTENT, Constantes.HTTP_CODE_NO_CONTENT,
                    Constantes.MSG_RESP_REMOVIDO);
        } catch (NotFoundRegisterException e) {
            return new RestResponse<>(HttpStatus.NOT_FOUND, Constantes.HTTP_CODE_NOT_FOUND, e.getMessage());
        }
    }
}
