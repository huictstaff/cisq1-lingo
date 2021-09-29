package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {
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



    @Test
    @DisplayName("hint given matches the hint expected")
    void hintWithFormerHint(){
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.ABSENT, Mark.CORRECT));

        String expectedHint = "woo-d";
        String formerHint = "wo---";

        assertEquals(expectedHint,feedback.giveHint(formerHint));
    }

    @Test
    @DisplayName("hint given matches the hint expected")
    void hintWithoutFormerHint(){
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.ABSENT, Mark.ABSENT));

        String expectedHint = "woo--";
        String formerHint = "";

        assertEquals(expectedHint,feedback.giveHint(formerHint));
    }

    @Test
    @DisplayName("hint given matches the hint expected")
    void hintButNothingInTheAttemptWasCorrect(){
        Feedback feedback = new Feedback("woord", List.of(Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT));

        String expectedHint = "jo---";
        String formerHint = "jo---";

        assertEquals(expectedHint,feedback.giveHint(formerHint));
    }

}
