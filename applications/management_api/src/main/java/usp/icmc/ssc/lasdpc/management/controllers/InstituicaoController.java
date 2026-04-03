package usp.icmc.ssc.lasdpc.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import usp.icmc.ssc.lasdpc.management.controllers.responses.PagedResponse;
import usp.icmc.ssc.lasdpc.management.controllers.responses.RestResponse;
import usp.icmc.ssc.lasdpc.management.dtos.InstituicaoDTO;
import usp.icmc.ssc.lasdpc.management.entities.Bloco;
import usp.icmc.ssc.lasdpc.management.entities.Instituicao;
import usp.icmc.ssc.lasdpc.management.exceptions.BusinessException;
import usp.icmc.ssc.lasdpc.management.exceptions.NotFoundRegisterException;
import usp.icmc.ssc.lasdpc.management.services.InstituicaoService;
import usp.icmc.ssc.lasdpc.management.utils.Constantes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/instituicoes")
public class InstituicaoController extends AbstractController {

    @Autowired
    private InstituicaoService instituicaoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_VIEWER')")
    public PagedResponse<InstituicaoDTO> get(
            @RequestParam(name = "pagNum", defaultValue = Constantes.NUMERO_PAGINA_PADRAO, required = false) int pagNum,
            @RequestParam(name = "pagSize", defaultValue = Constantes.TAMANHO_PAGINA_PADRAO, required = false) int pagSize) {
        Page<Instituicao> page = this.instituicaoService.all(pagNum, pagSize);

        PagedResponse<InstituicaoDTO> response = new PagedResponse<InstituicaoDTO>();
        response.setContent(page.getContent().stream().map(InstituicaoDTO::from)
                .collect(Collectors.toList()));
        response.setPage(page.getNumber());
        response.setSize(page.getSize());
        response.setLast(page.isLast());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());

        return response;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<InstituicaoDTO> create(@RequestBody InstituicaoDTO instituicaoDTO) {
        try {
            Instituicao save = this.instituicaoService.save(Instituicao.from(instituicaoDTO));
            return new RestResponse<>(HttpStatus.CREATED, Constantes.HTTP_CODE_CREATED,
                    Constantes.MSG_RESP_CADASTRO, InstituicaoDTO.from(save));
        } catch (BusinessException e) {
            return new RestResponse<>(HttpStatus.UNPROCESSABLE_ENTITY,
                    Constantes.HTTP_CODE_UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<InstituicaoDTO> getById(@PathVariable("id") final UUID id) {
        try {
            Instituicao instituicao = this.instituicaoService.find(id);
            return new RestResponse<>(HttpStatus.OK, Constantes.HTTP_CODE_OK, Constantes.MSG_RESP_CONSULTA,
                    InstituicaoDTO.from(instituicao));
        } catch (NotFoundRegisterException e) {
            return new RestResponse<>(HttpStatus.NOT_FOUND, Constantes.HTTP_CODE_NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<InstituicaoDTO> update(
            @RequestBody Instituicao instituicao,
            @PathVariable("id") UUID id) {
        try {
            Instituicao updated = this.instituicaoService.save(instituicao);
            return new RestResponse<>(HttpStatus.OK, Constantes.HTTP_CODE_OK, Constantes.MSG_RESP_ALTERACAO,
                    InstituicaoDTO.from(updated));
        } catch (BusinessException e) {
            return new RestResponse<>(HttpStatus.UNPROCESSABLE_ENTITY, Constantes.HTTP_CODE_UNPROCESSABLE_ENTITY,
                    e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<InstituicaoDTO> delete(UUID id) {
        try {
            this.instituicaoService.delete(id);
            return new RestResponse<>(HttpStatus.NO_CONTENT, Constantes.HTTP_CODE_NO_CONTENT,
                    Constantes.MSG_RESP_REMOVIDO);
        } catch (NotFoundRegisterException e) {
            return new RestResponse<>(HttpStatus.NOT_FOUND, Constantes.HTTP_CODE_NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}/blocos")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_VIEWER')")
    public RestResponse<List<Bloco>> getBlocos(@PathVariable("id") final UUID id) {
        try {
            List<Bloco> blocos = this.instituicaoService.getBlocos(id);
            return new RestResponse<>(HttpStatus.OK, Constantes.HTTP_CODE_OK, Constantes.MSG_RESP_CONSULTA, blocos);
        } catch (NotFoundRegisterException e) {
            return new RestResponse<>(HttpStatus.NOT_FOUND, Constantes.HTTP_CODE_NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}/blocos/paginados")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PagedResponse<Bloco> getBlocosPaginados(
            @PathVariable("id") final UUID id,
            @RequestParam(name = "pagNum", defaultValue = Constantes.NUMERO_PAGINA_PADRAO, required = false) int pagNum,
            @RequestParam(name = "pagSize", defaultValue = Constantes.TAMANHO_PAGINA_PADRAO, required = false) int pagSize) {
        try {
            Page<Bloco> page = this.instituicaoService.getBlocos(id, pagNum, pagSize);

            PagedResponse<Bloco> response = new PagedResponse<>();
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
