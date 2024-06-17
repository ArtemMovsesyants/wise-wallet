package ua.dp.amovsesyants.wisewallet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.dp.amovsesyants.wisewallet.dto.auth.AuthenticationResponse;
import ua.dp.amovsesyants.wisewallet.dto.auth.LoginRequest;
import ua.dp.amovsesyants.wisewallet.dto.auth.RegisterRequest;
import ua.dp.amovsesyants.wisewallet.service.AuthenticationService;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody final RegisterRequest registerRequest) {
        return authenticationService.register(registerRequest);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody final LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }

}
