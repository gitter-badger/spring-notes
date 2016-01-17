package cz.sycha.inventory.cz.sycha.inventory.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "No such Note")
public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException() {
        super();
    }

    public NoteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoteNotFoundException(String message) {
        super(message);
    }

    public NoteNotFoundException(Throwable cause) {
        super(cause);
    }
}
