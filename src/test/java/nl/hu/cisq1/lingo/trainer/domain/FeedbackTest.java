package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    @Test
    @DisplayName("Word is guessed if all letters are correct")
    private void wordIsGuessed(){
        List<Mark> result = List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT);
        Feedback f = new Feedback("woord", result);

        assertTrue(f.isWordGuessed());
    }
    @Test
    @DisplayName("Some letters are incorrect")
    private void wordIsNotGuessed(){
        List<Mark> result = List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.INVALID, Mark.CORRECT);
        Feedback f = new Feedback("woord", result);

        assertFalse(f.isWordGuessed());
    }

    @Test
    @DisplayName("Guess is valid")
    private void guessIsValid(){
        List<Mark> result = List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT);
        Feedback f = new Feedback("woord", result);

        assertTrue(f.isGuessValid());
    }

    @Test
    @DisplayName("Guess invalid")
    private void guessIsInvalid(){
        List<Mark> result = List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT);
        Feedback f = new Feedback("woo", result);

        assertFalse(f.isGuessValid());
    }

    @ParameterizedTest
    @DisplayName(value = "provide hints")
    @MethodSource("provideHintExamples")
    private void giveHint(String wordToGuess, String attempt, char[] hint){
        List<Mark> result = List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT);
        //reformat marks to parameterized tests also
    }


    private static Stream<Arguments> provideHintExamples(){
        return Stream.of(
                Arguments.of("woord", "wezel", new char[]{'w', '.', '.', '.', '.'}),
                Arguments.of("beter", "boter", new char[]{'b', '.', 't', 'e', 'r'}),
                Arguments.of("hoogtes", "higgess", new char[]{'h', '.', '.', 'g', '.', '.', '.'})
        );
    }

}