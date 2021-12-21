package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import nl.hu.cisq1.lingo.trainer.domain.enums.State;
import nl.hu.cisq1.lingo.trainer.domain.exception.GameOverException;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidWordLengthException;
import nl.hu.cisq1.lingo.trainer.domain.exception.RoundActiveException;
import nl.hu.cisq1.lingo.trainer.domain.exception.RoundNotExistException;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Game {

    private int score;
    private int wordLength;
    private State status;

    private final List<Round> rounds;

    public Game() {
        this.score = 0;
        this.wordLength = 5;
        this.status = State.ACTIVE;
        this.rounds = new ArrayList<>();
    }

    public void startNewRound(String answer) {
        if (answer.length() != wordLength) {
            throw new InvalidWordLengthException();
        }

        if (this.rounds.size() > 0) {
            if (getLastRound().getStatus() == State.ACTIVE) {
                throw new RoundActiveException();
            }

            if (getLastRound().getStatus() == State.LOST) {
                throw new GameOverException();
            }
        }

        Round round = new Round(answer);
        this.rounds.add(round);
        setNextWordLength();
    }

    public String guess(String attempt) {
        Round round = getLastRound();
        round.guess(attempt);

        if (round.getStatus() == State.WON) {
            this.score = this.score + (5 * (5 - round.getAttempts().size()) + 5);
        } else if (round.getStatus() == State.LOST) {
            this.status = State.LOST;
        }

        return getCurrentHint();
    }

    public String getCurrentHint() {
        if (rounds.size() == 0) {
            throw new RoundNotExistException();
        }

        return rounds.get(rounds.size() - 1).getHint();
    }

    private Round getLastRound() {
        if (rounds.size() == 0) {
            throw new RoundNotExistException();
        }

        return rounds.get(rounds.size() - 1);
    }

    private void setNextWordLength() {
        this.wordLength = (this.wordLength - 4) % 3 + 5;
    }

}
