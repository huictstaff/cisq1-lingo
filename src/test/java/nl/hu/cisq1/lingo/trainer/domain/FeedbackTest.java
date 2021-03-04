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

    @Test
    @DisplayName("Word is guessed if all letters are correct")
    void wordIsGuessed(){
        List<Mark> result = List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT);
        Feedback f = new Feedback(result);

        assertTrue(f.isWordGuessed());
    }
    @Test
    @DisplayName("Some letters are incorrect")
    void wordIsNotGuessed(){
        List<Mark> result = List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.INVALID, Mark.CORRECT);
        Feedback f = new Feedback(result);

        assertFalse(f.isWordGuessed());
    }

    @Test
    @DisplayName("Guess is valid")
    void guessIsValid(){
        List<Mark> result = List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT);
        Feedback f = new Feedback(result);

        assertTrue(f.isGuessValid());
    }

    @Test
    @DisplayName("Guess invalid")
    void guessIsInvalid(){
        List<Mark> result = List.of(Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID);
        Feedback f = new Feedback(result);

        assertFalse(f.isGuessValid());
    }
    //TODO fix deze test
    /*@ParameterizedTest
    @DisplayName(value = "provide hints")
    @MethodSource("provideHintExamples")
    void giveHint(String wordToGuess, List<Character> hint){
        List<Mark> result = List.of(Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID);
        Feedback f = new Feedback(result);
        ArrayList<Character> c = new ArrayList<>();

        assertEquals(f.giveHint(c, wordToGuess), hint);
    }

    private static Stream<Arguments> provideHintExamples(){
        return Stream.of(
                Arguments.of("woord", List.of('w', '.', '.', '.', '.')),
                Arguments.of("beter", List.of('b', '.', 't', 'e', 'r')),
                Arguments.of("hoogtes", List.of('h', '.', '.', 'g', '.', '.', 's'))
        );
    }*/
}