package nl.hu.cisq1.lingo.trainer.domain.exception;

public class GameException extends RuntimeException {
    public GameException(String message) {
        super(message);
    }



    public static GameException gameHasAlreadyStarted() {
        return new GameException(
                String.format(
                        "Game has already started | Can't start an already started game"
                )
        );
    }

    public static GameException gameIsNotRunning() {
        return new GameException(
                String.format(
                        "game isn't running | game hasn't yet started"
                )
        );
    }


    public static GameException currentRoundHasntYetEnded() {
        return new GameException(
                String.format(
                        "Can't start a new round | current round hasn't yet ended"
                )
        );
    }

    public static GameException gameOver(String msg) {
        return new GameException(msg);
    }


    public static Exception gameHasNoRoundsYet() {
        return new GameException(
                String.format(
                        "Game has no rounds yet"
                )
        );

    }
}
