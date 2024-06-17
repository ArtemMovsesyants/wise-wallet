package ua.dp.amovsesyants.wisewallet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.dp.amovsesyants.wisewallet.dao.RoleRepository;
import ua.dp.amovsesyants.wisewallet.dao.UserRepository;
import ua.dp.amovsesyants.wisewallet.dto.auth.AuthenticationResponse;
import ua.dp.amovsesyants.wisewallet.dto.auth.LoginRequest;
import ua.dp.amovsesyants.wisewallet.dto.auth.RegisterRequest;
import ua.dp.amovsesyants.wisewallet.entity.Role;
import ua.dp.amovsesyants.wisewallet.entity.User;
import ua.dp.amovsesyants.wisewallet.enums.RoleName;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(final RegisterRequest registerRequest) {
        Set<RoleName> roleNames = registerRequest.getRoleNames();

        Set<Role> roles = roleNames.stream()
                .map(roleName ->
                        roleRepository
                                .findByName(roleName)
                                .orElseThrow(() -> new NoSuchElementException(String.format(
                                                "Role with roleName '%s' not found",
                                                roleName)
                                        )
                                )
                )
                .collect(Collectors.toSet());
        User user = new User(
                registerRequest.getUsername(),
                passwordEncoder.encode(registerRequest.getPassword()),
                roles
        );

        userRepository.saveAndFlush(user);

        return new AuthenticationResponse(jwtService.generateToken(user));
    }

    public AuthenticationResponse login(final LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();

        return new AuthenticationResponse(jwtService.generateToken(user));
    }
}
