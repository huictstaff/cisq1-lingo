package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class RoundTest {
    private Score score = mock(Score.class);
    private Word word = mock(Word.class);
    private Feedback feedback = mock(Feedback.class);

    @Test
    @DisplayName("Test if the number of rounds is greater than 5")
    void numberOfRounds(){

    }

    @ParameterizedTest
    @MethodSource("attemptsExamples")
    @DisplayName("Get random word from words")
    void compareAttemptToWord(String wordToGuess, String attempt, List<Mark> marks){

    }

    private static Stream<Arguments> attemptsExamples(){
        return Stream.of(
                Arguments.of("woord", "wezel", List.of(Mark.CORRECT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT)),
                Arguments.of(("beter", "boter", List.of(Mark.CORRECT, Mark.PRESENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT)),
                Arguments.of("hoogtes", "higgess", List.of(Mark.CORRECT, Mark.ABSENT, Mark.PRESENT, Mark.CORRECT, Mark.PRESENT, Mark.PRESENT, Mark.CORRECT))
        );
    }
    /*
    Shows random word
    Displays random letter
    retrieves input word attempt
    Compares attempt with given word
    Returns Feedback
    */

}
