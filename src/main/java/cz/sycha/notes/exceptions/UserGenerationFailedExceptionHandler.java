package cz.sycha.notes.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserGenerationFailedExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = UserGenerationFailedException.class)
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        Map<String, String> body = new HashMap<String, String>();
        body.put("error", "User generation failed");

        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
