package cz.sycha.notes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "User generation failed for no obvious reason")
public class UserGenerationFailedException extends RuntimeException {
    public UserGenerationFailedException() {
        super();
    }

    public UserGenerationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserGenerationFailedException(String message) {
        super(message);
    }

    public UserGenerationFailedException(Throwable cause) {
        super(cause);
    }
}
