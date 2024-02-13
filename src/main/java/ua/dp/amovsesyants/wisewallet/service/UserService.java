package ua.dp.amovsesyants.wisewallet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.dp.amovsesyants.wisewallet.dao.UserRepository;
import ua.dp.amovsesyants.wisewallet.entity.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

}
