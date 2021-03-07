package nl.hu.cisq1.lingo.trainer.domain.exception;

public class GameEndedException extends RuntimeException{
    public GameEndedException() {
        super("This Game has been ended !");
    }
}
