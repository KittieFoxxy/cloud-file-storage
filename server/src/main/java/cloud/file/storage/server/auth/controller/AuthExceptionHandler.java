package cloud.file.storage.server.auth.controller;

import cloud.file.storage.server.auth.dto.ErrorResponse;
import cloud.file.storage.server.auth.exception.UserAlreadyExistException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
public class AuthExceptionHandler {

    private final MessageSource messageSource;

    AuthExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException ex, Locale locale) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(messageSource.getMessage(ex.getLocalizedMessage(),
                        new Object[0],
                        ex.getMessage(),
                        locale))
                );
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistException(UserAlreadyExistException ex, Locale locale) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(messageSource.getMessage("auth.errors.user.exist",
                        new Object[0],
                        ex.getMessage(),
                        locale))
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, Locale locale) {
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse(messageSource.getMessage("server.errors.internal",
                        new Object[0],
                        ex.getMessage(),
                        locale))
                );
    }
}
