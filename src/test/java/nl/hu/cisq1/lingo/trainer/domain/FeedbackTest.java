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
    void hintIsCorrect(){
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.ABSENT, Mark.CORRECT));

        String expectedHint = "woo-d";
        List<Character> expectedHintList = new ArrayList<>();

        for (char ch: expectedHint.toCharArray()) {
            expectedHintList.add(ch);
        }
        assertEquals(expectedHintList,feedback.giveHint());


    }


}
