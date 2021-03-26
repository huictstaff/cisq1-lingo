package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Data;
import lombok.SneakyThrows;
import nl.hu.cisq1.lingo.trainer.domain.enums.GameStatus;
import nl.hu.cisq1.lingo.trainer.domain.exception.GameException;
import nl.hu.cisq1.lingo.words.application.WordService;
import java.util.ArrayList;
import java.util.List;

@Data
public class Game {
    private GameStatus gameStatus;
    private List<Round> rounds;
    private WordService wordService;
    private double score;

    public Game() {
        this.rounds = new ArrayList<>();
        this.score = 0.0;
        this.gameStatus = GameStatus.WAITING_FOR_ROUND;
    }

    public void startGame(Round round) throws GameException {
        if (isGameStarted())
            throw GameException.gameHasAlreadyStarted();
        if (getGameStatus() == GameStatus.ELIMINATED)
            throw GameException.gameOver("Can't restart a terminated game");
        this.gameStatus = GameStatus.PLAYING;
        nextRound(round);
    }

    public void nextRound(Round round) throws GameException {
        if (round == null) throw new GameException("Round can't be null");
        if (!isGameStarted())  throw GameException.gameIsNotRunning();
        if (getGameStatus() == GameStatus.ELIMINATED) throw GameException.gameOver("Game isn't running | game has already ended");
        if (isLastRoundStillPlaying()) throw GameException.currentRoundHasntYetEnded();

        if (!rounds.isEmpty()) {
            // add to game score based on previous round's resul
            this.score += calculateScore(getLastRound());
        }

        if (round.isGuessed()) {
            this.score += calculateScore(round);
            this.gameStatus = GameStatus.WAITING_FOR_ROUND;
        }

        this.rounds.add(round);

    }


    public double calculateScore(Round round) {
        return 5 * (5 - round.numOfTriedAttempts()) + 5;
    }

    public boolean isLastRoundStillPlaying() throws GameException {
        return (rounds.isEmpty()) ? false : getLastRound().roundIsRunning();
    }

    @SneakyThrows
    private Round getLastRound() throws GameException {
        if (rounds.isEmpty()) throw GameException.gameHasNoRoundsYet();
        return rounds.get(rounds.size() - 1);
    }

    public boolean isGameStarted() {
        return !(this.gameStatus == GameStatus.WAITING_FOR_ROUND && this.rounds.isEmpty());
    }

    public GameStatus getGameStatus() {
        if (this.gameStatus == GameStatus.ELIMINATED) return this.gameStatus;
        if (!isGameStarted()) return GameStatus.WAITING_FOR_ROUND;
//        if (!rounds.isEmpty() && !getLastRound().roundIsRunning()) return GameStatus.WAITING_FOR_ROUND;
//        if (!rounds.isEmpty() && getLastRound().roundIsRunning()) return GameStatus.PLAYING;
        if (!isLastRoundStillPlaying()) return GameStatus.WAITING_FOR_ROUND;
        if (isLastRoundStillPlaying()) return GameStatus.PLAYING;

        return this.gameStatus;
    }

    public void eliminateGame() throws GameException {
        this.gameStatus = GameStatus.ELIMINATED;
        if (!rounds.isEmpty()) getLastRound().terminate();
    }

}
