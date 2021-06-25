package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.Round;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidLengthException;
import nl.hu.cisq1.lingo.trainer.domain.exception.RoundIsOverException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    public static Stream<Arguments> provideWords() {
        return Stream.of(
                Arguments.of("woord"),
                Arguments.of("klein")
        );
    }

    @ParameterizedTest
    @MethodSource("provideWords")
    @DisplayName("round is made when it appears last in the rounds list in the game object")
    void roundMade(String word) {
        Game game = new Game();
        Round round = game.makeRound(word);
        List<Round> rounds = game.getRounds();
        assertEquals(rounds.get(rounds.size() - 1), round);
    }

    @Test
    @DisplayName("Word is guessed when the returned hint is the same as the word")
    void guessWord() {
        Game game = new Game();
        game.makeRound("woord");
        assertEquals(List.of("w", "o", "o", "r", "d"), game.guessWord("woord").getHintStrings());
    }

    @Test
    @DisplayName("It should throw the exception when a round is won or when the user runs out of guesses")
    void throwsRoundIsOverException() {
        Game game = new Game();
        game.makeRound("woord");
        game.getLastRound().setGuesses(0);
        assertThrows(RoundIsOverException.class, () -> game.guessWord("woord"));
        game.getLastRound().setGuesses(5);
        game.getLastRound().setWon(true);
        assertThrows(RoundIsOverException.class, () -> game.guessWord("woord"));
        game.getLastRound().setGuesses(0);
        assertThrows(RoundIsOverException.class, () -> game.guessWord("woord"));
        game.getLastRound().setGuesses(5);
        game.getLastRound().setWon(false);
    }

    @Test
    @DisplayName("It should throw the exception when the guessed word is not the same length as the word to guess.")
    void throwsInvalidLengthException() {
        Game game = new Game();
        game.makeRound("woord");
        assertThrows(InvalidLengthException.class, () -> game.guessWord(""));
    }

    @Test
    @DisplayName("when the round is not won you should have one less guess.")
    void RoundIsNotWon() {
        Game game = new Game();
        game.makeRound("woord");
        System.out.println(String.join("", game.guessWord("appel").getHintStrings()));
        assertFalse(game.getLastRound().getWon());
    }
}