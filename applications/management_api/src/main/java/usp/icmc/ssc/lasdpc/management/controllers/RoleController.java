package usp.icmc.ssc.lasdpc.management.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import usp.icmc.ssc.lasdpc.management.controllers.responses.RestResponse;
import usp.icmc.ssc.lasdpc.management.dtos.RoleDTO;
import usp.icmc.ssc.lasdpc.management.entities.ERole;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<RoleDTO> getRoles() {
        List<RoleDTO> roles = new ArrayList<>();

        for (ERole role: ERole.values()) {
            if (role == ERole.ROLE_ADMIN) roles.add(new RoleDTO("admin", "Administrador"));
            if (role == ERole.ROLE_USER) roles.add(new RoleDTO("user", "Usuário"));
            if (role == ERole.ROLE_VIEWER) roles.add(new RoleDTO("viewer", "Visualizador"));
        }

        return roles;
    }

}
