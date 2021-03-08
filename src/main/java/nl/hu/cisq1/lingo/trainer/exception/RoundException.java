package nl.hu.cisq1.lingo.trainer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RoundException extends RuntimeException{
    public RoundException(String message) {
        super(message);
    }
}
