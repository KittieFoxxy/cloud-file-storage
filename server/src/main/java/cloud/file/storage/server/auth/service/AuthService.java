package cloud.file.storage.server.auth.service;

import cloud.file.storage.server.auth.exception.UserAlreadyExistException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserDetailsManager userDetailsManager;

    AuthService(UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    public void signUp(String username, String password) throws UserAlreadyExistException {
        if (userDetailsManager.userExists(username)) {
            throw new UserAlreadyExistException("auth.errors.user.exist");
        }
        userDetailsManager.createUser(User.withUsername(username)
                .password(password)
                .authorities(new String[0])
                .build());
    }
}
