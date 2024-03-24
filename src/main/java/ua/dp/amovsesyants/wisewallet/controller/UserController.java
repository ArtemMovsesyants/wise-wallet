package ua.dp.amovsesyants.wisewallet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.dp.amovsesyants.wisewallet.dto.GetUserResponse;
import ua.dp.amovsesyants.wisewallet.service.UserService;

import java.util.Set;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public Set<GetUserResponse> getUsers() {
        return userService.getUsers();
    }

}
