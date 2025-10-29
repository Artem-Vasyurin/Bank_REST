package vasyurin.work.bank_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vasyurin.work.bank_rest.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}

