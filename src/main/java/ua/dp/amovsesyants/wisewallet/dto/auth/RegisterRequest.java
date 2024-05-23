package ua.dp.amovsesyants.wisewallet.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String city;
    private String username;
    private String password;
}
