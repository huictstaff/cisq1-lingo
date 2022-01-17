package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {


    @Test
    @DisplayName("word is guessed if all letters are correct")
    void wordIsGuessed(){
        Feedback feedback = new Feedback("woord", List.of(Mark.Correct,Mark.Correct,Mark.Correct,Mark.Correct,Mark.Correct));
        assertTrue(feedback.isWordGuessed());
    }
    @Test
    @DisplayName("word is not guessed if all latters are not correct")
    void wordIsNotGuessed(){
        Feedback feedback = new Feedback("woord", List.of(Mark.Absent,Mark.Correct,Mark.Correct,Mark.Correct,Mark.Correct));
        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("word guessed is valid")
    void guessIsValid(){
        Feedback feedback = new Feedback("woord", List.of(Mark.Absent,Mark.Correct,Mark.Correct,Mark.Correct,Mark.Correct));
        assertTrue(feedback.guessIsValid());
    }

    @Test
    @DisplayName("word guessed is not valid")
    void guessIsNotValid(){
        Feedback feedback = new Feedback("wos", List.of(Mark.Absent,Mark.Correct,Mark.Correct,Mark.Correct,Mark.Correct));
        assertFalse(feedback.guessIsValid());
    }

    @ParameterizedTest
    @DisplayName("hint are given based on previous hints and feedback")
    @MethodSource("provideHintExamples")
    void giveHints(String previusHint, String guess,List<Mark> marks, String nextHint) throws Exception {
        Feedback feedback = new Feedback(guess, marks);
        assertEquals(nextHint,feedback.giveHint(previusHint));


    }

    static Stream<Arguments> provideHintExamples() {
        return Stream.of(
                Arguments.of("w....","waard", List.of(Mark.Correct,Mark.Absent,Mark.Absent,Mark.Correct,Mark.Correct),"w..rd"),
                Arguments.of("w..rd","woerd",List.of(Mark.Correct,Mark.Correct,Mark.Absent,Mark.Correct,Mark.Correct),"wo.rd"),
                Arguments.of("wo.rd", "woord",List.of(Mark.Correct,Mark.Correct,Mark.Correct,Mark.Correct,Mark.Correct),"woord")

        );
    }
}