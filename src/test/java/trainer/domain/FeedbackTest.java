package trainer.domain;

import nl.hu.cisq1.lingo.words.application.WordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import trainer.application.TrainerService;
import trainer.data.SpringGameRepository;
import trainer.domain.exception.InvalidFeedbackException;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FeedbackTest {
    @Test
    @DisplayName("Error is thrown when the amount of marks and letters are not equal")
    void throwsInvalidFeedbackException() {
        assertThrows(
                InvalidFeedbackException.class,
                () -> new Feedback("woord", List.of(Mark.CORRECT))
        );
    }

    public static Stream<Arguments> provideHintExamples() {
        return Stream.of(
                Arguments.of("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT)),
                Arguments.of("klein", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT))
        );
    }


    @ParameterizedTest
    @MethodSource("provideHintExamples")
    @DisplayName("Hint is correct if the given marks correspond to the correct symbol")
        // correct symbols are:
        // PRESENT = "*"
        // ABSENT = "_"
        // CORRECT = The letter at that place in the word
        // INVALID = "#"
    void hintGiven(String word, List<Mark> marks) {
        WordService wordService = mock(WordService.class);
        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
        TrainerService service = new TrainerService(wordService, gameRepository);
        when(wordService.provideRandomWord(anyInt())).thenReturn("appel");

        Feedback feedback = new Feedback(word, List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        Hint hint = feedback.giveHint(word, marks);
        for (int i = 0; i < marks.size(); i++) {
            switch (marks.get(i)) {
                case PRESENT -> assertEquals(hint.getHintStrings().get(i), "*");
                case ABSENT -> assertEquals(hint.getHintStrings().get(i), "_");
                case CORRECT -> assertEquals(hint.getHintStrings().get(i), "" + word.charAt(i));
                case INVALID -> assertEquals(hint.getHintStrings().get(i), "#");
                default -> System.err.println("Error Invalid mark.");
            }
        }
    }

    @Test
    @DisplayName("Word is guessed if all letters are correct.")
    void wordIsGuessed() {
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertTrue(feedback.getMarks().stream().allMatch(mark -> mark == Mark.CORRECT));
    }

    @Test
    @DisplayName("Word is not guessed if one or more letters are not correct.")
    void wordIsNotGuessed() {
        Feedback feedback = new Feedback("woord", List.of(Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertFalse(feedback.getMarks().stream().allMatch(mark -> mark == Mark.CORRECT));
    }

    @Test
    @DisplayName("Guess is invalid if one or more characters are invalid.")
    void guessIsInvalid() {
        Feedback feedback = new Feedback("woord", List.of(Mark.INVALID, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertTrue(feedback.getMarks().stream().anyMatch(mark -> mark == Mark.INVALID));
    }

    @Test
    @DisplayName("Guess is valid if none of the characters are invalid.")
    void guessIsNotInvalid() {
        Feedback feedback = new Feedback("woord", List.of(Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertFalse(feedback.getMarks().stream().anyMatch(mark -> mark == Mark.INVALID));
    }
}