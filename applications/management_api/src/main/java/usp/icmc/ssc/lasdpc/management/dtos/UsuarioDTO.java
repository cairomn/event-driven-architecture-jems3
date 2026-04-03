package usp.icmc.ssc.lasdpc.management.dtos;

import usp.icmc.ssc.lasdpc.management.entities.ERole;
import usp.icmc.ssc.lasdpc.management.entities.Role;
import usp.icmc.ssc.lasdpc.management.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UsuarioDTO {

    private Long id;

    private String nome;

    private String email;

    private List<String> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public static UsuarioDTO from(User user) {
        List<String> roles = new ArrayList<>();

        for (Role role: user.getRoles()) {
            if (role.getName() == ERole.ROLE_ADMIN) {
                roles.add("admin");
            }

            if (role.getName() == ERole.ROLE_USER) {
                roles.add("user");
            }

            if (role.getName() == ERole.ROLE_VIEWER) {
                roles.add("viewer");
            }
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(user.getId());
        usuarioDTO.setNome(user.getNome());
        usuarioDTO.setEmail(user.getEmail());
        usuarioDTO.setRoles(roles);
        return usuarioDTO;
    }
}
