package nl.hu.cisq1.lingo.trainer.domain.exception;

public class RoundActiveException extends RuntimeException {
    public RoundActiveException() {
        super("a round is already active");
    }
}
