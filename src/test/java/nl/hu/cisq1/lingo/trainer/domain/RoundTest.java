package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.INVALID;
import static nl.hu.cisq1.lingo.trainer.domain.Mark.ABSENT;
import static nl.hu.cisq1.lingo.trainer.domain.Mark.PRESENT;
import static nl.hu.cisq1.lingo.trainer.domain.Mark.CORRECT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RoundTest {
    private static Stream<Arguments> provideFeedbackExamples() {
        return Stream.of(
                Arguments.of("sheep", "ship", List.of(INVALID, INVALID, INVALID, INVALID)),
                Arguments.of("sheep", "ships", List.of(CORRECT, CORRECT, ABSENT, PRESENT, ABSENT)),
                Arguments.of("sheep", "shear", List.of(CORRECT, CORRECT, CORRECT, ABSENT, ABSENT)),
                Arguments.of("sheep", "sheet", List.of(CORRECT, CORRECT, CORRECT, CORRECT, ABSENT)),
                Arguments.of("sheep", "sheep", List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT))
        );
    }

    @ParameterizedTest
    @MethodSource("provideFeedbackExamples")
    @DisplayName("attempt must have corresponding feedback")
    void attemptHasFeedback(String wordToGuess, String attempt, List<Mark> result) {
        Round round = new Round(wordToGuess);
        round.guessWord(attempt);
        assertEquals(result, round.getFeedbacks().get(0).getMarks());
    }

    @Test
    @DisplayName("attempt must be saved after a guess")
    void attemptIsSaved() {
        Round round = new Round("word");
        round.guessWord("wolf");
        assertEquals("wolf", round.getAttempts().get(0));
    }

    @Test
    @DisplayName("word to guess must be saved after round creation")
    void wordIsSaved() {
        Round round = new Round("word");
        assertEquals("word", round.getWordToGuess());
    }
}