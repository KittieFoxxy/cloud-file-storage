package cloud.file.storage.server.auth.controller;

import cloud.file.storage.server.auth.dto.LoginRequest;
import cloud.file.storage.server.auth.dto.LoginResponse;
import cloud.file.storage.server.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<LoginResponse> signUp(@Valid @RequestBody LoginRequest loginRequest) {
            authService.signUp(loginRequest.username(), loginRequest.password());
            LoginResponse response = new LoginResponse(loginRequest.username());
            return ResponseEntity
                    .created(URI.create("/api/user/me"))
                    .body(response);
    }
}
