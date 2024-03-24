package ua.dp.amovsesyants.wisewallet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.dp.amovsesyants.wisewallet.dao.UserRepository;
import ua.dp.amovsesyants.wisewallet.dto.GetUserResponse;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Set<GetUserResponse> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(GetUserResponse::new)
                .collect(Collectors.toSet());
    }

}
