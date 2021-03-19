package nl.hu.cisq1.lingo.trainer.domain.exception;

public class InvalidCharacterException extends RuntimeException {
    public InvalidCharacterException() {
        super("Guess contains invalid characters");
    }
}
