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
public class CustomExceptionHandlers extends ResponseEntityExceptionHandler {
    /**
     * NoteNotFoundException handler
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = NoteNotFoundException.class)
    protected ResponseEntity<Object> handleNoteNotFoundException(RuntimeException ex, WebRequest request) {
        //Object body = new Object(){String error = "Note not found";};
        Map<String, String> body = new HashMap<String, String>();
        body.put("error", "Note not found");

        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    /**
     * WrongCredentialsException handler
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = WrongCredentialsException.class)
    protected ResponseEntity<Object> handleWrongCredentialsException(RuntimeException ex, WebRequest request) {
        Map<String, String> body = new HashMap<String, String>();
        body.put("error", "Wrong credentials");
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    /**
     * UserAlreadyExistsException handler
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = UserAlreadyExistsException.class)
    protected ResponseEntity<Object> handleUserAlreadyExistsException(RuntimeException ex, WebRequest request) {
        Map<String, String> body = new HashMap<String, String>();
        body.put("error", "User with this username already exists");
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
