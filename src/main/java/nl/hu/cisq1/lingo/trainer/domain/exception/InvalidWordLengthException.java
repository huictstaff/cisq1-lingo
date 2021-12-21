package nl.hu.cisq1.lingo.trainer.domain.exception;

public class InvalidWordLengthException extends RuntimeException {
    public InvalidWordLengthException() {
        super("Invalid word length");
    }
}
