package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.Round;
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
    public void roundMade(String word) {
        Game game = new Game();
        Round round = game.makeRound(word);
        List<Round> rounds = game.getRounds();
        assertEquals(rounds.get(rounds.size() - 1), round);
    }

    @Test
    @DisplayName("Word is guessed when the returned hint is the same as the word")
    public void guessWord() {
        Game game = new Game();
        game.makeRound("woord");
        assertEquals(List.of("w", "o", "o", "r", "d"), game.guessWord("woord").getHintStrings());
        assertThrows(RoundIsOverException.class, () -> game.guessWord(""));
    }

    @Test
    void setId() {
        Game game = new Game();
        game.setId(Long.parseLong("3"));
        assertEquals(Long.parseLong("3"), game.getId());
    }

    @Test
    void setRounds() {
        Game game = new Game();
        game.setRounds(List.of(new Round()));
        assertEquals(List.of(new Round()).toString(), game.getRounds().toString());
    }

    @Test
    void getLastRound() {
        Game game = new Game();
        game.makeRound("woord");
        assertEquals(new Round("woord", game).toString(), game.getLastRound().toString());

    }
}