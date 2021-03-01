package nl.hu.cisq1.lingo.trainer.domain.exception;

public class GameRoundException extends RuntimeException {
    public GameRoundException(String message) {
        super(message);
    }

    public static GameRoundException guessAttemptsSurpassWordLength(int wordLength) {
        return new GameRoundException(
                String.format(
                        "number of attempts surpasses word length (%s)",
                        wordLength
                )
        );
    }


    public static GameRoundException cantGetHintsForAnEndedRound() {
        return new GameRoundException(
                String.format(
                        "Can't gets hinst anymore for an ended round"
                )
        );
    }


    public static GameRoundException noMoreHintsLeft() {
        return new GameRoundException(
                String.format(
                        "no more hints available left for this round"
                )
        );
    }

    public static GameRoundException noAttemptsYet() {
        return new GameRoundException(
                String.format(
                        "can't give feedback on no attempts"
                )
        );
    }


}
