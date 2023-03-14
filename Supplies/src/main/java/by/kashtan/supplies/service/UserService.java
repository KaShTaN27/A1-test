package by.kashtan.supplies.service;

import by.kashtan.supplies.model.User;
import by.kashtan.supplies.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> saveAll(List<User> users) {
        return userRepository.saveAll(users.stream()
                .filter(u -> !userRepository.existsByAccountName(u.getAccountName()))
                .toList());
    }
}
