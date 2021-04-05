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
        this.points = 0;
    }

    public Game(int id) {
        this.id = id;
        this.rounds = new ArrayList<>();
        this.gameState = GameState.CONTINUE;
        this.points = 0;
    }

    public void newRound(String word) {
        if (this.gameState.equals(GameState.LOST)) {
            throw new GameLostException();
        }

        if (this.round != null) {
            throw new ActiveRoundException();
        }
        this.gameState = GameState.CONTINUE;
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
        int score = 0;
        for (Round r : this.rounds) {
            if (r.getGameState() == GameState.WON) {
                score += 5 * (5 - r.getGuesses().size()) + 5;
            }
        }
        this.points = score;
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
        calculateScore();
        this.gameState = this.checkGameState();
        this.round = null;
    }

    public long getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }
}
