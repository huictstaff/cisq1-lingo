package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidCharacterException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.*;
import static org.junit.jupiter.api.Assertions.*;

class RoundTest {

    @Test
    @DisplayName("constructor should not fail")
    void constructor() {
        assertDoesNotThrow(() -> new Round("lingo"));
    }

    @Test
    @DisplayName("First hint should give the first letter")
    void firstHint() {
        Round round = new Round("lingo");
        assertEquals(List.of('l', '-', '-', '-', '-'), round.giveFirstHint().getHint());
    }

    @Test
    @DisplayName("makeGuess should do nothing when guess is invalid")
    void makeGuessInvalid() {
        Round round = new Round("lingo");
        assertThrows(InvalidCharacterException.class, () -> round.makeGuess("long😀"));
        assertEquals(0, round.getFeedbackList().size());
    }

    @Test
    @DisplayName("makeGuess should handle guessing correctly when guess is valid")
    void makeGuessValid() {
        Round round = new Round("lingo");
        round.makeGuess("longe");
        assertEquals(round.getGuesses().get(0).getGuess(), "longe");
        assertEquals(round.getFeedbackList().get(0).getMarks(), List.of(CORRECT, PRESENT, CORRECT, CORRECT, ABSENT));
    }

    @Test
    @DisplayName("State should be lost after 4 incorrect guesses")
    void determineStateLost() {
        Round round = new Round("worsten");
        round.makeGuess("wnetsro");
        round.makeGuess("wetsron");
        round.makeGuess("wtsrone");
        round.makeGuess("wsronet");
        round.makeGuess("wortsen");
        round.determineState(round.getFeedbackList().get(round.getFeedbackList().size() - 1));
        assertEquals(GameState.LOST, round.getGameState());
    }

    @Test
    @DisplayName("State should be lost after 4 incorrect guesses")
    void determineStateWon() {
        Round round = new Round("worsten");
        round.makeGuess("wnetsro");
        round.makeGuess("wetsron");
        round.makeGuess("wtsrone");
        round.makeGuess("wsronet");
        round.makeGuess("worsten");
        round.determineState(round.getFeedbackList().get(round.getFeedbackList().size() - 1));
        assertEquals(GameState.WON, round.getGameState());
    }

    @Test
    @DisplayName("State should be lost after 4 incorrect guesses")
    void determineStateContinue() {
        Round round = new Round("worsten");
        round.makeGuess("wnetsro");
        round.makeGuess("wetsron");
        round.makeGuess("wtsrone");
        round.makeGuess("wsronet");
        round.determineState(round.getFeedbackList().get(round.getFeedbackList().size() - 1));
        assertEquals(GameState.CONTINUE, round.getGameState());
    }

    @Test
    @DisplayName("GetHint returns correct hint")
    void getHing() {
        Round round = new Round("worsten");
        round.makeGuess("wotseet");
        round.makeGuess("worstne");
        round.getHint().giveHint(round.getGuesses(), round.getFeedbackList(), round.getWord());
        assertEquals(List.of('w', 'o', 'r', 's', 't', 'e', '+'), round.getHint().giveHint(round.getGuesses(), round.getFeedbackList(), round.getWord()));
    }
}