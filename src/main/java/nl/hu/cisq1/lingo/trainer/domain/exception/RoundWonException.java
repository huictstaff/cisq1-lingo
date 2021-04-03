package nl.hu.cisq1.lingo.trainer.domain.exception;

public class RoundWonException extends RuntimeException {
    public RoundWonException() {
        super("Round is won, start a new round");
    }
}
