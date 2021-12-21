package nl.hu.cisq1.lingo.trainer.domain.exception;

public class GameOverException extends RuntimeException {
    public GameOverException() {
        super("game is over");
    }
}
