package nl.hu.cisq1.lingo.domain;

import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class RoundTest {

    @Test
    @DisplayName("Start round and first letter of word is shown")
    void showFirstLetterOfWord() {
        Word word = new Word("woord");

        Round round = new Round(1,  word, new ArrayList<>());

        assertEquals(Arrays.asList('w', '_', '_', '_', '_'), round.startRound());
    }

    @ParameterizedTest(name = "Test #{index} | {0} | {1} | {2} " )
    @DisplayName("Attempt compared to word")
    @MethodSource("provideGuessExamples")
    void guessWord(String word, String attempt, List<Character> hint) {
        Round round = new Round(1, new Word(word), new ArrayList<>());

        round.startRound();
        Feedback feedback = round.guessWord(attempt);

        assertEquals(hint, feedback.giveHint());
    }

    private static Stream<Arguments> provideGuessExamples() {
        return Stream.of(
                Arguments.of("woord", "woord", Arrays.asList('w', 'o', 'o', 'r', 'd')),
                Arguments.of("woord", "soort", Arrays.asList('.', 'o', 'o', 'r', '.')),
                Arguments.of("woord", "breuk", Arrays.asList('.', '+', '.', '.', '.'))
        );
    }
}