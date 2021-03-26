package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.enums.Mark;
import nl.hu.cisq1.lingo.trainer.domain.exception.GameRoundException;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {

    @Test
    public void tryAGuessHappyFlow() {
        Round round = new Round(new Word("woord"));
        round.tryAGuess(new Word("wooof"));
        assertEquals(1, round.numOfTriedAttempts());
        assertEquals(3, round.numOfAttemptsLeft());
    }

    @Test
    public void attempsSurpassTriesLimit() {
        Round round = new Round(new Word("woord"));
        round.tryAGuess(new Word("wooof"));
        round.tryAGuess(new Word("wooof"));
        round.tryAGuess(new Word("wooof"));
        round.tryAGuess(new Word("wooof"));


        assertEquals(0, round.numOfAttemptsLeft());

        assertThrows(GameRoundException.class,
                () -> round.tryAGuess(new Word("wooof")));
    }

    @Test
    public void getFeedbackOnNoAttempt() {

        Round newlyStartedRound = new Round(new Word("woord"));
        assertThrows(GameRoundException.class,
                () -> newlyStartedRound.getLatestFeedback());
    }


    @Test
    public void roundStillRunning(){
        Round round = new Round(new Word("woord"));
        assertTrue(round.roundIsRunning());
    }


    @Test
    public void roundHasEndedAfterNoMoreAttemptsLeft() {
        Round round = new Round(new Word("woord"));
        round.tryAGuess(new Word("wooof"));
        round.tryAGuess(new Word("wooof"));
        round.tryAGuess(new Word("wooof"));
        round.tryAGuess(new Word("wooof"));
        assertFalse(round.roundIsRunning());
    }


    @Test
    public void wordIsGuessed() {
        Round round = new Round(new Word("woord"));
        round.tryAGuess(new Word("wooof"));
        round.tryAGuess(new Word("woord"));

        assertTrue(round.isGuessed());
    }

    @Test
    public void getHintedLetters() {
        Round round = new Round(new Word("woord"));
        assertEquals(new Hint(List.of('W', '.', '.', '.', '.')), round.getHintedLetters());
        round.tryAGuess(new Word("wooof"));
        assertEquals(new Hint(List.of('W', 'O', 'O', '.', '.')), round.getHintedLetters());
    }


    @Test
    public void generateFeedbackCorrect() {
        Round round = new Round(new Word("woord"));
        assertEquals(Feedback.forCorrect("woord"), round.genFeedback(List.of('W', 'O', 'O', 'R', 'D')));
    }

    @Test
    public void generateFeedbackInvalid() {
        Round round = new Round(new Word("woord"));
        assertEquals(Feedback.forInvalid("ooord"), round.genFeedback(List.of('O', 'O', 'O', 'R', 'D')));
        assertEquals(Feedback.forInvalid("wwoord"), round.genFeedback(List.of('W', 'W', 'O', 'O', 'R', 'D')));
    }

    @Test
    public void generateFeedbackMixed() {
        Round round = new Round(new Word("woord"));
        assertEquals(new Feedback("wodud", List.of(Mark.CORRECT, Mark.CORRECT, Mark.PRESENT, Mark.ABSENT, Mark.CORRECT)), round.genFeedback(List.of('W', 'O', 'D', 'U', 'D')));
    }


    @Test
    public void wordIsGuessedWithTheHelpOfAHint() {
        Round round = new Round(new Word("woord"));
        round.tryAGuess(new Word("woorr"));
        round.getAHint();
        assertTrue(round.isGuessed());
        assertFalse(round.roundIsRunning());
    }

    @Test
    public void wordIsNotGuessedYet() {
        Round round = new Round(new Word("woord"));
        round.tryAGuess(new Word("wooof"));
        assertFalse(round.isGuessed());
    }



    @Test
    public void roundHasEndedAfterGuessed() {
        Round round = new Round(new Word("woord"));
        round.tryAGuess(new Word("wooof"));
        round.tryAGuess(new Word("woord"));

        assertTrue(round.isGuessed());
        assertFalse(round.roundIsRunning());
    }

    @Test
    public void roundHasEndedAfterGuessedButNoMoreAttemptsLeft() {
        Round round = new Round(new Word("woord"));
        round.tryAGuess(new Word("wooof"));
        round.tryAGuess(new Word("wooof"));
        round.tryAGuess(new Word("wooof"));
        round.tryAGuess(new Word("woord"));

        assertTrue(round.isGuessed());
        assertFalse(round.roundIsRunning());
    }


    @Test
    public void getHintForAnEndedRound() {
        Round round = new Round(new Word("woord"));
        round.tryAGuess(new Word("wooof"));
        round.tryAGuess(new Word("wooof"));
        round.tryAGuess(new Word("wooof"));
        round.tryAGuess(new Word("wooof"));

        assertEquals(0, round.numOfAttemptsLeft());
        assertThrows(GameRoundException.class,
                () -> round.getAHint());
    }

}