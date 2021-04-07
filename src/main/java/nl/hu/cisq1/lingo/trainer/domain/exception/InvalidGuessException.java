package nl.hu.cisq1.lingo.trainer.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Guess is not valid")
public class InvalidGuessException extends RuntimeException {
}
