package nl.hu.cisq1.lingo.trainer.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Maximum amount of guesses reached, create a new round")
public class MaximumAttemptsReachedException extends RuntimeException {
}
