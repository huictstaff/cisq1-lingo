package nl.hu.cisq1.lingo.lingoTrainer.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RoundTest {

    @Test
    @DisplayName("Test new round at existing game")
    public void testNewGameRound()
    {
        System.out.println("test new game round");

        var game1 = new Game("huis");

        var feedbackFirstRound = game1.getCurrentRound().guessWord("huis");

        game1.newRound("auto");
        var feedbackSecondRound = game1.getCurrentRound().guessWord("huis");

        assertTrue(feedbackFirstRound.isWordGuessed() != feedbackSecondRound.isWordGuessed());
    }
    
    @Test
    void testConstructor() {
        Round actualRound = new Round("Word To Guess");
        actualRound.setPreviousHint("Previous Hint");
        actualRound.setWordToGuess("Word To Guess");
        assertEquals("Previous Hint", actualRound.getPreviousHint());
        assertEquals("Word To Guess", actualRound.getWordToGuess());
    }

    @Test
    void testConstructor2() {
        Round actualRound = new Round("Word To Guess");
        assertEquals("W,.,.,.,.,.,.,.,.,.,.,.,.", actualRound.getPreviousHint());
        assertEquals(0, actualRound.timesGuessed);
        assertEquals("Word To Guess", actualRound.getWordToGuess());
    }

    @Test
    void testGuessWord() {
        Round round = new Round("42");
        Feedback actualGuessWordResult = round.guessWord("Attempt");
        assertEquals("Feedback{attempt='null', mark=[ABSENT, ABSENT]}", actualGuessWordResult.toString());
        ArrayList<Mark> markList = actualGuessWordResult.marks;
        assertEquals(2, markList.size());
        assertEquals(Mark.ABSENT, markList.get(0));
        assertEquals(Mark.ABSENT, markList.get(1));
        assertEquals(1, round.timesGuessed);
    }

    @Test
    void testGuessWord2() {
        Round round = new Round("42");
        Feedback actualGuessWordResult = round.guessWord("42");
        assertEquals("Feedback{attempt='null', mark=[CORRECT, CORRECT]}", actualGuessWordResult.toString());
        ArrayList<Mark> markList = actualGuessWordResult.marks;
        assertEquals(2, markList.size());
        assertEquals(Mark.CORRECT, markList.get(0));
        assertEquals(Mark.CORRECT, markList.get(1));
        assertEquals(1, round.timesGuessed);
    }
}

