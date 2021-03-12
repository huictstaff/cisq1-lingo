package nl.hu.cisq1.lingo.domain;

import nl.hu.cisq1.lingo.domain.exception.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    @Test
    @DisplayName("Round is created right and gives first hint.")
    void roundIsCreated() {
        Round round = new Round("broodje");
        Feedback feedback = round.getRoundFeedback().get(0);
        List<Character> hint = List.of('b', '.', '.', '.', '.', '.', '.');
        assertEquals(hint, feedback.giveHint("broodje"));
    }
    @Test
    @DisplayName("Do guess gives right feedback")
    void doGuessGivesNewFeedback() {
        Round round = new Round("broodje");
        Feedback feedback = new Feedback("broeder", List.of(Rating.CORRECT, Rating.CORRECT, Rating.CORRECT, Rating.PRESENT, Rating.CORRECT, Rating.PRESENT, Rating.PRESENT));
        assertEquals(feedback, round.doGuess("broeder"));
    }

    @Test
    @DisplayName("Do guess with wrong amount letters gives error")
    void doWrongGuessGivesError() {
        Round round = new Round("broodje");
        assertThrows(InvalidGuessLengthException.class,
                () -> round.doGuess("broede"));
    }

    @Test
    @DisplayName("Check if doing a guess is added to the list")
    void doGuessAddsFeedbackToList() {
        Round round = new Round("broodje");
        round.doGuess("broeder");
        round.doGuess("bievaks");
        assertEquals(3, round.getRoundFeedback().size()); //3 because first hint, is also a feedback object.
    }

    @Test
    @DisplayName("Test if trying an 6th guess is being blocked.")
    void exceedMaximumGuesses() {
        Game game = new Game("testGame");
        game.getLastRound().setRoundOver(RoundStatus.WORD_IS_GUESSED);
        Round round = new Round("broodje");
        round.setGame(game);
        round.doGuess("broeder");
        round.doGuess("bievaks");
        round.doGuess("bievaks");
        round.doGuess("bievaks");
        round.doGuess("bievaks");
        assertThrows(ForbiddenGuessException.class,
                () -> round.doGuess("broeder"));
    }

    @Test
    @DisplayName("Test if guess is forbidden after word is guessed")
    void guessIsForbiddenAfterRightGuess(){
        Round round = new Round("broodje");
        round.doGuess("broedje");
        round.doGuess("broodje");
        assertThrows(ForbiddenGuessException.class,
                () -> round.doGuess("broeder"));
    }

    @Test
    @DisplayName("Test if last guess does'nt get exceeded by max attempts")
    void testLastRightGuess() {
        Round round = new Round("broodje");
        round.doGuess("broeder");
        round.doGuess("bievaks");
        round.doGuess("bievaks");
        round.doGuess("bievaks");
        round.doGuess("broodje");
        assertEquals(RoundStatus.WORD_IS_GUESSED, round.getRoundOver());
    }
}