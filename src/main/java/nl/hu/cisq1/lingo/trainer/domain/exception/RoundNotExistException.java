package nl.hu.cisq1.lingo.trainer.domain.exception;

public class RoundNotExistException extends RuntimeException {
    public RoundNotExistException() {
        super("no round exists for game");
    }
}
