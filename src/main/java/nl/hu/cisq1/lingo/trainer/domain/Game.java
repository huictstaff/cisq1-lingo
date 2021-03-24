package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.ActiveRoundException;
import nl.hu.cisq1.lingo.trainer.domain.exception.GameLostException;

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
        if (this.round != null) {
            throw new ActiveRoundException();
        } else if (this.gameState.equals(GameState.LOST)) {
            throw new GameLostException();
        }
        this.rounds.add(new Round(word));
    }

    public void makeGuess(String guess) {
        this.round.makeGuess(guess);
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
    }
}
