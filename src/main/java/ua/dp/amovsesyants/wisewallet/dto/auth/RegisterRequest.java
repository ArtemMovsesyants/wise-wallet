package ua.dp.amovsesyants.wisewallet.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.dp.amovsesyants.wisewallet.enums.RoleName;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String city;
    private String username;
    private String password;
    private Set<RoleName> roleNames;
}
