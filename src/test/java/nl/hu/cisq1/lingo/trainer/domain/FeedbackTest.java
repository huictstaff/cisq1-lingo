package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    // word is guessed right
    @Test
    void testWordIsGuessed() {
        var feedback = new Feedback("Appel", "Appel");
        feedback.calculateMarks();
        assertTrue((feedback.wordIsGuessed()));
    }

    // Word is guessed wrong
    @Test
    void testWordIsNotGuessed() {
        var feedback = new Feedback("Appel", "Guess");
        feedback.calculateMarks();
        assertFalse((feedback.wordIsGuessed()));
    }


    @Test
    void testCalculateMarks() {
        Feedback feedback = new Feedback("appel", "toets");
        ArrayList<Mark> actualCalculateMarksResult = feedback.calculateMarks();
        assertSame(feedback.marks, actualCalculateMarksResult);
        assertEquals(5, actualCalculateMarksResult.size());
        assertEquals(Mark.ABSENT, actualCalculateMarksResult.get(0));
        assertEquals(Mark.ABSENT, actualCalculateMarksResult.get(1));
        assertEquals(Mark.ABSENT, actualCalculateMarksResult.get(2));
        assertEquals(Mark.ABSENT, actualCalculateMarksResult.get(3));
        assertEquals(Mark.ABSENT, actualCalculateMarksResult.get(4));

        assertSame(actualCalculateMarksResult, feedback.getMarks());
    }

    @Test
    void testCalculateMarks2() {
        Feedback feedback = new Feedback("appel", "appel");
        ArrayList<Mark> actualCalculateMarksResult = feedback.calculateMarks();
        assertSame(feedback.marks, actualCalculateMarksResult);
        assertEquals(5, actualCalculateMarksResult.size());
        assertEquals(Mark.CORRECT, actualCalculateMarksResult.get(0));
        assertEquals(Mark.CORRECT, actualCalculateMarksResult.get(1));
        assertEquals(Mark.CORRECT, actualCalculateMarksResult.get(2));
        assertEquals(Mark.CORRECT, actualCalculateMarksResult.get(3));
        assertEquals(Mark.CORRECT, actualCalculateMarksResult.get(4));

        assertSame(actualCalculateMarksResult, feedback.getMarks());
    }
}

