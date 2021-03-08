package nl.hu.cisq1.lingo.trainer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidWordException extends RuntimeException{
    public InvalidWordException(String message) {
        super(message);
    }
}
