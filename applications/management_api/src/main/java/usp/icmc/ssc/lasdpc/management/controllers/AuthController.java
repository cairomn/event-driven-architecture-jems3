package usp.icmc.ssc.lasdpc.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import usp.icmc.ssc.lasdpc.management.controllers.requests.LoginRequest;
import usp.icmc.ssc.lasdpc.management.controllers.requests.SignupRequest;
import usp.icmc.ssc.lasdpc.management.controllers.responses.JwtResponse;
import usp.icmc.ssc.lasdpc.management.controllers.responses.RestResponse;
import usp.icmc.ssc.lasdpc.management.entities.ERole;
import usp.icmc.ssc.lasdpc.management.entities.Role;
import usp.icmc.ssc.lasdpc.management.entities.User;
import usp.icmc.ssc.lasdpc.management.repositories.RoleRepository;
import usp.icmc.ssc.lasdpc.management.repositories.UserRepository;
import usp.icmc.ssc.lasdpc.management.security.jwt.JwtUtils;
import usp.icmc.ssc.lasdpc.management.services.UserDetailsImpl;
import usp.icmc.ssc.lasdpc.management.utils.Constantes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/valid")
    public boolean isValidToken(@RequestBody String token) {
        return jwtUtils.validateJwtToken(token);
    }
}
