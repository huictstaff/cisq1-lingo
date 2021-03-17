package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private final int id = 1;
    private final int score = 1;
    private final String woord = "woord";
    private final Round round = new Round(woord);
    private final List<Round> rounds = new ArrayList<>(List.of(round));
    private final Game game = new Game(id, score, rounds, round);

    @Test
    @DisplayName("calling the constructor with just an id and initial word should create round with that word")
    void newGameObjectWithId() {
        Game game = new Game(this.id + 1, this.woord);
        assertEquals(this.woord, game.getActiveRound().getWordToGuess());
    }

    @Test
    @DisplayName("starting a new round should return the active round")
    void newRoundWhenActiveRound() {
        Round originalRound = this.game.getActiveRound();
        this.game.newRound("worod");
        assertEquals(this.game.getActiveRound(), originalRound);
    }

    @Test
    @DisplayName("starting a new round when the previous round is finished should make that the active round")
    void newRoundWhenPreviousRoundIsDone() {
        this.game.getActiveRound().setState(State.GUESSED);
        Round round = this.game.newRound("woord");
        assertEquals(this.game.getActiveRound(), round);
    }

    @Test
    void getId() {
        assertEquals(this.game.getId(), this.id);
    }

    @Test
    void getScore() {
        assertEquals(this.game.getScore(), this.score);
    }

    @Test
    void getRounds() {
        assertEquals(this.game.getRounds().get(0).getWordToGuess(), this.woord);
    }

    @Test
    void getActiveRound() {
        assertEquals(this.game.getActiveRound().getWordToGuess(), this.woord);
    }

    @Test
    void setId() {
        int id = 8;
        this.game.setId(id);
        assertEquals(this.game.getId(), id);
    }

    @Test
    void setScore() {
        int score = 50;
        this.game.setScore(score);
        assertEquals(this.game.getScore(), score);
    }

    @Test
    void setRounds() {
        List<Round> rounds = new ArrayList<>();
        rounds.add(new Round("worod"));
        this.game.setRounds(rounds);
        assertEquals(this.game.getRounds(), rounds);
    }

    @Test
    void setActiveRound() {
        Round round = new Round("worod");
        this.game.setActiveRound(round);
        assertEquals(this.game.getActiveRound(), round);
    }
}