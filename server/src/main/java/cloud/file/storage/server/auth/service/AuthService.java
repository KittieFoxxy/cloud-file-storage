package cloud.file.storage.server.auth.service;

import cloud.file.storage.server.auth.exception.UserAlreadyExistException;
import org.springframework.context.MessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;

    AuthService(UserDetailsManager userDetailsManager,
                PasswordEncoder passwordEncoder,
                MessageSource messageSource) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
        this.messageSource = messageSource;
    }

    public void signUp(String username, String password) throws UserAlreadyExistException {
        if (userDetailsManager.userExists(username)) {
            throw new UserAlreadyExistException();
        }
        userDetailsManager.createUser(User.withUsername(username)
                .password(passwordEncoder.encode(password))
                .authorities(new SimpleGrantedAuthority("ROLE_USER"))
                .build());
    }
}
