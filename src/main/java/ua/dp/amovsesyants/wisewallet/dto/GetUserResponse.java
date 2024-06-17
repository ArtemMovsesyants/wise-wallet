package ua.dp.amovsesyants.wisewallet.dto;

import lombok.Getter;
import ua.dp.amovsesyants.wisewallet.entity.Role;
import ua.dp.amovsesyants.wisewallet.entity.User;
import ua.dp.amovsesyants.wisewallet.enums.RoleName;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class GetUserResponse {
    private String username;
    private Set<RoleName> roles;

    public GetUserResponse(final User user) {
        this.username = user.getUsername();
        this.roles = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

    }
}
