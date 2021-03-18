package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.exception.InvalidAttemptException;
import nl.hu.cisq1.lingo.trainer.exception.InvalidFeedbackException;
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
    @DisplayName("word is guessed if all letters are correct")
    void isWordToGuess ( ) {
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT));
        assertTrue(feedback.isWordGuessed());
    }
    @Test
    @DisplayName("word is not guessed if not all letters are correct")
    void isWordNotToGuess ( ) {
        Feedback feedback = new Feedback("woord", List.of(Mark.ABSENT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT));
        assertFalse(feedback.isWordGuessed());
    }
    @Test
    @DisplayName("word is valid when the word correct and has a same amount letters")
    void isWordValid ( ) {
        Feedback feedback = new Feedback("woord", List.of(Mark.ABSENT,Mark.PRESENT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT));
        assertTrue(feedback.isWordVlid());
    }
    @Test
    @DisplayName("word is not valid when the word  not correct or has a few or to many amount letters")
    void isWordNotValid ( ) {
        Feedback feedback = new Feedback("woord", List.of(Mark.INVALID,Mark.INVALID,Mark.INVALID,Mark.INVALID,Mark.INVALID));
        assertFalse(feedback.isWordVlid());
    }

    @Test
    @DisplayName("feedback is Not valid when the feedback length Not like attempt length  ")
    void isFeedbackValid ( ) {
        assertThrows(    InvalidFeedbackException.class,
                () -> new Feedback("woord", List.of(Mark.CORRECT)));
    }


    @ParameterizedTest(name = "Test #{index} | {0} | {1} | {2}| {3}| {4}| {5} | {6}")
    @MethodSource("provideHintExamples")
    @DisplayName("Show all marks when the attempt is correct")
    void provideFeedback(String attempt, List<Mark> marks, List<String> hint) {
        Feedback feedback = new Feedback(attempt, marks);
        assertEquals(hint, feedback.gaveHint());
    }

    static Stream<Arguments> provideHintExamples() {
        return Stream.of(
                Arguments.of("woord", List.of(Mark.CORRECT,Mark.ABSENT,Mark.ABSENT,Mark.ABSENT,Mark.CORRECT),
                        List.of("w",".",".",".","d")),
                Arguments.of("woord", List.of(Mark.ABSENT,Mark.CORRECT,Mark.PRESENT,Mark.CORRECT,Mark.ABSENT),
                        List.of(".","o","(o)","r",".")),
                Arguments.of("woord", List.of(Mark.CORRECT,Mark.PRESENT,Mark.CORRECT,Mark.PRESENT,Mark.CORRECT),
                        List.of("w","(o)","o","(r)","d")),
                Arguments.of("woord", List.of(Mark.PRESENT,Mark.ABSENT,Mark.ABSENT,Mark.ABSENT,Mark.PRESENT),
                        List.of("(w)",".",".",".","(d)")),
                Arguments.of("woord", List.of(Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT),
                        List.of("w","o","o","r","d")),
                Arguments.of("woord", List.of(Mark.ABSENT,Mark.ABSENT,Mark.ABSENT,Mark.ABSENT,Mark.ABSENT),
                        List.of(".",".",".",".",".")),
                Arguments.of("woord", List.of(Mark.PRESENT,Mark.PRESENT,Mark.PRESENT,Mark.PRESENT,Mark.PRESENT),
                        List.of("(w)","(o)","(o)","(r)","(d)"))
        );
    }

    @Test
    @DisplayName("hint is invalid when the feedback length Not like attempt length  ")
    void InvalidHitTest (){
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT,Mark.PRESENT, Mark.INVALID,Mark.CORRECT,Mark.ABSENT));
        assertThrows(    InvalidAttemptException.class,
                feedback::gaveHint);
    }
    @ParameterizedTest(name = "Test #{index} | {0} | {1} | {2}| {3}| {4}")
    @MethodSource("listMarksTesten")
    @DisplayName("When attempt and word to guess have a same length make for every letter feedback(correct, absent or present) else invalid ")
    void feedbackGeneratorTest(String attempt , String wordToGuess, List<Mark> marks){
        assertEquals(marks, Feedback.feedbackGenerator(attempt, wordToGuess));}
        static Stream<Arguments> listMarksTesten() {
            return Stream.of(
                    Arguments.of("word", "woord" ,List.of(Mark.INVALID,Mark.INVALID,Mark.INVALID,Mark.INVALID)),
                    Arguments.of("wonen","woord", List.of(Mark.CORRECT,Mark.CORRECT,Mark.ABSENT,Mark.ABSENT,Mark.ABSENT)),
                    Arguments.of("woord","woord", List.of(Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT)),
                    Arguments.of("worde","woord", List.of(Mark.CORRECT,Mark.CORRECT,Mark.PRESENT,Mark.PRESENT,Mark.ABSENT)),
                    Arguments.of("duren","woord", List.of(Mark.PRESENT,Mark.ABSENT,Mark.PRESENT,Mark.ABSENT,Mark.ABSENT))
            );

    }
}