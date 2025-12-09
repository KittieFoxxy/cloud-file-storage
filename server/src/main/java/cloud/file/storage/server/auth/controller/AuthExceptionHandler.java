package cloud.file.storage.server.auth.controller;

import cloud.file.storage.server.auth.dto.ErrorResponse;
import cloud.file.storage.server.auth.exception.UserAlreadyExistException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AuthExceptionHandler {

    private final MessageSource messageSource;

    AuthExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, Locale locale) {
        System.err.println(ex.getParameter());
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(messageSource.getMessage(ex.getLocalizedMessage(),
                        new Object[0],
                        ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(error -> error.getDefaultMessage())
                                .sorted(Comparator.reverseOrder())
                                .collect(Collectors.joining("\n")),
                        locale))
                );
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistException(Locale locale) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(messageSource.getMessage("auth.errors.user.exist",
                        new Object[0],
                        "auth.errors.user.exist",
                        locale))
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Locale locale) {
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse(messageSource.getMessage("server.errors.internal",
                        new Object[0],
                        "server.errors.internal",
                        locale))
                );
    }
}
