package vasyurin.work.bank_rest.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vasyurin.work.bank_rest.entities.User;
import vasyurin.work.bank_rest.repositories.UserRepository;

@AllArgsConstructor
@Service
public class PasswordService {
    private final UserRepository userRepository;

    public boolean checkPasswordOnLogin(User user, String rawPassword) {
        String stored = user.getPassword();
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        if (stored.startsWith("$2a$")) {
            if (encoder.matches(rawPassword, stored)) {
                user.setPassword("{bcrypt}" + stored);
                userRepository.save(user);
                return true;
            }
            return false;
        }
        return PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(rawPassword, stored);
    }
}
