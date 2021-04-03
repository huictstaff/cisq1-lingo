package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.ActiveRoundException;
import nl.hu.cisq1.lingo.trainer.domain.exception.GameLostException;
import nl.hu.cisq1.lingo.trainer.domain.exception.NoActiveRoundException;
import nl.hu.cisq1.lingo.trainer.domain.exception.RoundWonException;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int points;
    private GameState gameState;
    private long id;
    private Round round;
    private final List<Round> rounds;

    public Game() {
        this.rounds = new ArrayList<>();
        this.gameState = GameState.CONTINUE;
    }

    public Game(int id) {
        this.id = id;
        this.rounds = new ArrayList<>();
        this.gameState = GameState.CONTINUE;
    }

    public void newRound(String word) {
        if (this.gameState.equals(GameState.LOST)) {
            throw new GameLostException();
        }

        if (this.round != null) {
            throw new ActiveRoundException();
        }
        this.rounds.add(new Round(word));
        this.round = this.rounds.get(this.rounds.size() - 1);
    }

    public void makeGuess(String guess) {
        if (this.gameState == GameState.LOST) {
            throw new GameLostException();
        } else if (this.gameState == GameState.WON) {
            throw new RoundWonException();
        } else {
            this.round.makeGuess(guess);
            this.gameState = checkGameState();
            this.gameState = this.round.getGameState();
            if (this.gameState == GameState.LOST) {
                throw new GameLostException();
            }
        }
    }

    public GameState checkGameState() {
        return this.round.getGameState();
    }

    public void calculateScore() {
        if (this.gameState == GameState.WON) {
            this.points += 5 * (5 - this.round.getGuesses().size()) + 5;
        }
    }

    public int provideNextLenghtWord() {
        if (this.round == null) {
            throw new NoActiveRoundException();
        }
        return switch (this.round.getWord().length()) {
            case 5 -> 6;
            case 6 -> 7;
            default -> 5;
        };
    }

    public void endRound() {
        this.rounds.add(this.round);
        calculateScore();
        this.gameState = this.checkGameState();
        this.round = null;
    }
}
