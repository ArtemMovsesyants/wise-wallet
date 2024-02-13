package ua.dp.amovsesyants.wisewallet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.dp.amovsesyants.wisewallet.entity.User;
import ua.dp.amovsesyants.wisewallet.service.UserService;

import java.util.List;

@RestController("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

}
