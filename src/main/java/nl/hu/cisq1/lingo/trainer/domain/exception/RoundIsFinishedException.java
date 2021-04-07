package nl.hu.cisq1.lingo.trainer.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The round is finished, start a new one")
public class RoundIsFinishedException extends RuntimeException {
}
