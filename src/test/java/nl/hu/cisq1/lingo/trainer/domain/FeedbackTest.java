package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.*;
import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {
    @Test
    @DisplayName("word is guessed if all letters are correct")
    void wordIsGuessed() {
        Feedback feedback = new Feedback(List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT),"woord");
        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("word is not guessed if not all letters are correct")
    void wordIsNotGuessed() {
        Feedback feedback = new Feedback(List.of(CORRECT, ABSENT, CORRECT, CORRECT, CORRECT), "woord");
        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("word is invalid if all letters are invalid")
    void wordIsInvalid() {
        Feedback feedback = new Feedback(List.of(INVALID, INVALID, INVALID, INVALID, INVALID),"woord");
        assertTrue(feedback.isWordInvalid());
    }

    @Test
    @DisplayName("word is valid if not all letters are marked as invalid")
    void wordIsValid() {
        Feedback feedback = new Feedback(List.of(INVALID, ABSENT, CORRECT, CORRECT, CORRECT),"woord");
        assertFalse(feedback.isWordInvalid());
    }

    @Test
    @DisplayName("feedback is different if values different")
    void feedbackIsDifferent() {
        Feedback feedbackA = new Feedback(List.of(CORRECT, CORRECT),"ha");
        Feedback feedbackB = new Feedback(List.of(CORRECT, INVALID),"ha");

        assertNotEquals(feedbackB, feedbackA);
    }

    @Test
    @DisplayName("feedback is same if values same")
    void feedbackIsSame() {
        Feedback feedbackA = new Feedback(List.of(CORRECT, CORRECT),"ha");
        Feedback feedbackB = new Feedback(List.of(CORRECT, CORRECT),"ha");

        assertEquals(feedbackB, feedbackA);
    }

    @Test
    @DisplayName("hashcade is generated based on values")
    void hashCodeGeneration() {
        Feedback feedbackA = new Feedback(List.of(CORRECT, CORRECT),"ha");
        Feedback feedbackB = new Feedback(List.of(CORRECT, CORRECT),"ha");

        assertEquals(feedbackB.hashCode(), feedbackA.hashCode());
    }

    @Test
    @DisplayName("toString contains class name")
    void convertedToString() {
        Feedback feedbackA = new Feedback(List.of(CORRECT, CORRECT),"ha");
        assertTrue(feedbackA.toString().contains("Feedback"));
    }
//    @Test
//    @DisplayName("toString contains class name")
//    void InvalidLenght() {
//        assertThrows(
//                InvalidFeedbackException.class,
//                () -> new Feedback(List.of(CORRECT, CORRECT),"banaan")
//        );
//    }

    @Test
    @DisplayName("Feedback does not have the same size as the attempt")
    void InvalidLenght(){
        assertThrows(
                InvalidFeedbackException.class,
                () -> new Feedback(List.of(Mark.CORRECT), "woord")
        );
    }

}