package usp.icmc.ssc.lasdpc.management.services;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import usp.icmc.ssc.lasdpc.management.controllers.requests.SignupRequest;
import usp.icmc.ssc.lasdpc.management.dtos.UsuarioDTO;
import usp.icmc.ssc.lasdpc.management.entities.ERole;
import usp.icmc.ssc.lasdpc.management.entities.Role;
import usp.icmc.ssc.lasdpc.management.entities.User;
import usp.icmc.ssc.lasdpc.management.exceptions.BusinessException;
import usp.icmc.ssc.lasdpc.management.repositories.RoleRepository;
import usp.icmc.ssc.lasdpc.management.repositories.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;


    /**
     * Construtor da clase.
     *
     * @param userRepository
     */
    public UsuarioService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder encoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public Page<User> all(int numPag, int sizePage) {
        Pageable pageable = PageRequest.of(numPag, sizePage, Sort.by("nome").descending());
        return this.userRepository.findAll(pageable);
    }

    public UsuarioDTO getById(Long id) throws BusinessException {
        return UsuarioDTO.from(this.userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado")));
    }

    public UsuarioDTO salvar(@NotNull SignupRequest signUpRequest) throws BusinessException {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BusinessException("Error: Email is already in use!");
        }

        // Create new user's account
        User user = new User(signUpRequest.getNome(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
        user.setRoles(getRoles(signUpRequest.getRoles()));
        return UsuarioDTO.from(userRepository.save(user));
    }

    public UsuarioDTO update(@NotNull UsuarioDTO usuarioDTO) throws BusinessException {
        Optional<User> savedUser = this.userRepository.findById(usuarioDTO.getId());

        if (savedUser.isEmpty()) {
            throw new BusinessException("Usuário não encontrado");
        }

        User user = savedUser.get();
        user.setNome(usuarioDTO.getNome());
        user.setEmail(usuarioDTO.getEmail());
        Set<String> roles = new HashSet<>(usuarioDTO.getRoles());
        user.setRoles(getRoles(roles));
        return UsuarioDTO.from(this.userRepository.save(user));
    }

    private Set<Role> getRoles(Set<String> rolesUser) {
        Set<Role> roles = new HashSet<>();

        if (rolesUser == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_VIEWER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            rolesUser.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "user":
                        Role modRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_VIEWER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        return roles;
    }
}
