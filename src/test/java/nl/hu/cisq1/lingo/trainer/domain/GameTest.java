package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameTest {
    private final Long id = 1L;
    private final int score = 1;
    private final String woord = "woord";
    private final Round round = new Round(woord);
    private final List<Round> rounds = new ArrayList<>(List.of(round));
    private final Game game = new Game(id, score, rounds);
    private final Game emptyGame = new Game();

    @Test
    @DisplayName("calling the constructor with just an initial word should create round with that word")
    void newGameObjectWithId() {
        Game game = new Game(this.woord);
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
        Round round = this.game.newRound("worod");
        assertEquals(this.game.getActiveRound(), round);
    }

    @Test
    @DisplayName("when the active round is in progress you should receive a hint based on your guess")
    void guessingWithActiveRound() {
        String guess = "porot";
        this.game.guess("porod");
        List<Character> hints = this.game.guess(guess);
        assertEquals(hints.size(), guess.length());
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
        Long id = 8L;
        this.emptyGame.setId(id);
        assertEquals(this.emptyGame.getId(), id);
    }

    @Test
    void setScore() {
        int score = 50;
        this.emptyGame.setScore(score);
        assertEquals(this.emptyGame.getScore(), score);
    }

    @Test
    void setRounds() {
        List<Round> rounds = new ArrayList<>();
        rounds.add(new Round("worod"));
        this.emptyGame.setRounds(rounds);
        assertEquals(this.emptyGame.getRounds(), rounds);
    }

    @ParameterizedTest
    @MethodSource("nextWordInput")
    @DisplayName("next word length should be one higher than the previous or back to 5")
    void nextWordLength(String word, int nextLength) {
        this.emptyGame.setRounds(List.of(new Round(word)));
        int length = this.emptyGame.nextWordLength();
        assertEquals(nextLength, length);
    }

    private static Stream<Arguments> nextWordInput() {
        return Stream.of(
                Arguments.of("woord", 6),
                Arguments.of("wooord", 7),
                Arguments.of("woooord", 5)
        );
    }
}