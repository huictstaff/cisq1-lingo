package nl.hu.cisq1.lingo.trainer.domain.exception;

public class NoActiveRoundException extends RuntimeException {
    public NoActiveRoundException() {
        super("No active round");
    }
}
