package usp.icmc.ssc.lasdpc.management.controllers;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import usp.icmc.ssc.lasdpc.management.controllers.requests.SignupRequest;
import usp.icmc.ssc.lasdpc.management.controllers.responses.PagedResponse;
import usp.icmc.ssc.lasdpc.management.dtos.UsuarioDTO;
import usp.icmc.ssc.lasdpc.management.entities.User;
import usp.icmc.ssc.lasdpc.management.exceptions.BusinessException;
import usp.icmc.ssc.lasdpc.management.services.UsuarioService;
import usp.icmc.ssc.lasdpc.management.utils.Constantes;

import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PagedResponse<UsuarioDTO> get(
            @RequestParam(name = "pagNum", defaultValue = Constantes.NUMERO_PAGINA_PADRAO, required = false) int pagNum,
            @RequestParam(name = "pagSize", defaultValue = Constantes.TAMANHO_PAGINA_PADRAO, required = false) int pagSize) {
        Page<User> page = this.usuarioService.all(pagNum, pagSize);

        PagedResponse<UsuarioDTO> response = new PagedResponse<>();
        response.setContent(page.getContent().stream().map(UsuarioDTO::from)
                .collect(Collectors.toList()));
        response.setPage(page.getNumber());
        response.setSize(page.getSize());
        response.setLast(page.isLast());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());

        return response;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UsuarioDTO consultarUsuario(@PathVariable(name = "id") final Long id) throws BusinessException {
        return usuarioService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UsuarioDTO save(@RequestBody SignupRequest signupRequest) {
        try {
            return usuarioService.salvar(signupRequest);
        } catch (BusinessException e) {
            return null;
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UsuarioDTO update(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            return usuarioService.update(usuarioDTO);
        } catch (BusinessException e) {
            return null;
        }
    }
}
