package cz.sycha.notes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Wrong credentials")
public class WrongCredentialsException extends RuntimeException {
    public WrongCredentialsException() {
        super();
    }

    public WrongCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongCredentialsException(String message) {
        super(message);
    }

    public WrongCredentialsException(Throwable cause) {
        super(cause);
    }
}
