package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {
    public static Stream<Arguments> hintTest(){
        return Stream.of(
          Arguments.of(new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.ABSENT, Mark.CORRECT)), "wo---", "woo-d"),
          Arguments.of(new Feedback("woord", List.of(Mark.CORRECT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.CORRECT)), "w----", "w---d"),
          Arguments.of(new Feedback("woord", List.of(Mark.CORRECT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.CORRECT)), "", "w---d"),
          Arguments.of(new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT)), "", "woord"),
          Arguments.of(new Feedback("woord", List.of(Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT)), "woor-", "woor-")

        );
    }
    @Test
    @DisplayName("word is guessed if all letters are correct")
    void wordIsGuessed(){
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("word is not guessed because all the letters are incorrect")
    void wordIsNotGuessed(){
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.ABSENT));
        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("word is not guessed because all the letters are invalid")
    void guessIsInvalid(){
        Feedback feedback = new Feedback("woord", List.of(Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID));
        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("word is not guessed because all the letters are invalid")
    void guessIsNotInvalid(){
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertTrue(feedback.isWordGuessed());
    }
    
    @ParameterizedTest
    @MethodSource({"hintTest"})
    @DisplayName("Hint given depends on the attempted word andprevious hint")
    void giveHint(Feedback feedback, String formerHint, String expectedHint){
        assertEquals(expectedHint,feedback.giveHint(formerHint));
    }
}
