package nl.hu.cisq1.lingo.trainer.domain.exception;

public class GameLostException extends RuntimeException {
    public GameLostException() {
        super("Game is lost. Start a new game");
    }
}
