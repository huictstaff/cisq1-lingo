package nl.hu.cisq1.lingo.trainer.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class RoundTest {

    // Guessed wrong
    @Test
    void testGetGuessResultWrongGuess() {
        Round round = new Round("appel");
        Feedback actualGetGuessResultResult = round.GetGuessResult("Guess");
        assertEquals("Guess", actualGetGuessResultResult.getGuessWord());
        assertEquals("appel", actualGetGuessResultResult.getWordToGuess());
        ArrayList<Mark> marks = actualGetGuessResultResult.getMarks();
        assertEquals(5, marks.size());
        assertEquals(Mark.ABSENT, marks.get(0));
        assertEquals(Mark.ABSENT, marks.get(1));
        assertEquals(Mark.ABSENT, marks.get(2));
        assertEquals(Mark.ABSENT, marks.get(3));
        assertEquals(Mark.ABSENT, marks.get(4));

        assertEquals(1, round.getTimesGuessed());
    }

    // Guessed right
    @Test
    void testGetGuessResultCorrectGuess() {
        Round round = new Round("appel");
        Feedback actualGetGuessResultResult = round.GetGuessResult("appel");
        assertEquals("appel", actualGetGuessResultResult.getGuessWord());
        assertEquals("appel", actualGetGuessResultResult.getWordToGuess());
        ArrayList<Mark> marks = actualGetGuessResultResult.getMarks();
        assertEquals(5, marks.size());
        assertEquals(Mark.CORRECT, marks.get(0));
        assertEquals(Mark.CORRECT, marks.get(1));
        assertEquals(Mark.CORRECT, marks.get(2));
        assertEquals(Mark.CORRECT, marks.get(3));
        assertEquals(Mark.CORRECT, marks.get(4));

        assertEquals(1, round.getTimesGuessed());
    }
}

