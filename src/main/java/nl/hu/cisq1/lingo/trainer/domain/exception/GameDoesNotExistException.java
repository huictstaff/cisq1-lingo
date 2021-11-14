package nl.hu.cisq1.lingo.trainer.domain.exception;

public class GameDoesNotExistException extends RuntimeException {
    public GameDoesNotExistException(Long gameId) {
        super("Could not find game with id " + gameId);
    }
}
