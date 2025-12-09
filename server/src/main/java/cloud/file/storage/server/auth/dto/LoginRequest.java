package cloud.file.storage.server.auth.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "{auth.errors.user.username_is_blank}")
        @Size(min = 6, max = 50, message = "{auth.errors.user.username_size_invalid}")
        String username,
        @NotBlank(message = "{auth.errors.user.password_is_blank}")
        @Size(min = 6, max = 500, message = "{auth.errors.user.password_size_invalid}")
        String password) {
}
