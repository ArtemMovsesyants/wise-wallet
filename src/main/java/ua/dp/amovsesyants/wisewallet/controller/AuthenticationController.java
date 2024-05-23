package ua.dp.amovsesyants.wisewallet.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.dp.amovsesyants.wisewallet.dto.auth.AuthResponse;
import ua.dp.amovsesyants.wisewallet.dto.auth.LoginRequest;
import ua.dp.amovsesyants.wisewallet.dto.auth.RegisterRequest;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest registerRequest) {
        return null;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        return null;
    }

}
