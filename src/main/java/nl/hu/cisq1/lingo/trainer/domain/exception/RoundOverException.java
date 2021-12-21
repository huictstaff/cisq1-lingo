package nl.hu.cisq1.lingo.trainer.domain.exception;

public class RoundOverException extends RuntimeException {
    public RoundOverException() {
        super("Round over");
    }
}