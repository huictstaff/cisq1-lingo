package nl.hu.cisq1.lingo.trainer.domain.exception;

public class ActiveRoundException extends RuntimeException {
    public ActiveRoundException() {
        super("There is still an active round");
    }
}
