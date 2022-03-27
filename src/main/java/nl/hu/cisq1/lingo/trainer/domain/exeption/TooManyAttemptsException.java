package nl.hu.cisq1.lingo.trainer.domain.exeption;

public class TooManyAttemptsException extends RuntimeException {

    public TooManyAttemptsException(String message) {
        super(message);
    }
}
