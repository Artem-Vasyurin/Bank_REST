package vasyurin.work.bank_rest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vasyurin.work.bank_rest.dto.UserDto;
import vasyurin.work.bank_rest.dto.UserRequestDto;
import vasyurin.work.bank_rest.entities.Role;
import vasyurin.work.bank_rest.entities.User;
import vasyurin.work.bank_rest.repositories.UserRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
        return toDto(user);
    }

    public UserDto createUser(UserRequestDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.valueOf(dto.getRole()));
        Timestamp now = new Timestamp(System.currentTimeMillis());
        user.setCreated(now);
        user.setUpdated(now);

        return toDto(userRepository.save(user));
    }

    public UserDto updateUser(int id, UserRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
        user.setUsername(dto.getUsername());
        user.setRole(Role.valueOf(dto.getRole()));
        user.setUpdated(new Timestamp(System.currentTimeMillis()));

        return toDto(userRepository.save(user));
    }

    public void deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }

    private UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole().toString());
        dto.setCreated(user.getCreated());
        dto.setUpdated(user.getUpdated());
        return dto;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }
}


