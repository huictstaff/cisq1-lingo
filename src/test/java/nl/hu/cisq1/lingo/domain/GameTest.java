package nl.hu.cisq1.lingo.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @ParameterizedTest(name = "Test #{index} | {0} | {1} | {2} " )
    @DisplayName("score based on attempts in round")
    @MethodSource("provideAttemptExamples")
    void addScore(String word, int attempts, int score) {
        Game game = new Game(1L,  0, new ArrayList<>());

        game.newRound(new Word(word));

        Round round = game.getRounds().get(game.getRounds().size() -1);

        game.addScore(round, attempts);

        assertEquals(score, game.getScore());
    }

    private static Stream<Arguments> provideAttemptExamples() {
        return Stream.of(
                Arguments.of("woord", 1, 25),
                Arguments.of("woord", 2, 20),
                Arguments.of("woord", 3, 15),
                Arguments.of("woord", 4, 10),
                Arguments.of("woord", 5, 5)
        );
    }

    @Test
    @DisplayName("add one new round to game")
    void newRound() {
        Game game = new Game(1L,  0, new ArrayList<>());

        game.newRound(new Word("woord"));

        assertEquals(1, game.getRounds().size());
    }
}