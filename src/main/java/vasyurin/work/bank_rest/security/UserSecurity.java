package vasyurin.work.bank_rest.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vasyurin.work.bank_rest.services.UserService;

@Component("userSecurity")
@RequiredArgsConstructor
public class UserSecurity {

    private final UserService userService;
    public boolean isUserOwner(String username, int userId) {
        var user = userService.findByUsername(username);
        return user != null && user.getId() == userId;
    }
}
