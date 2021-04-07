package nl.hu.cisq1.lingo.trainer.domain.exception;

import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundIsFinishedExceptionTest {
    @Test
    @DisplayName("game should throw this error when guessing on an inactive round")
    void inactiveRound() {
        Game game = new Game("woord");
        game.getActiveRound().setState(State.LOST);
        assertThrows(RoundIsFinishedException.class, () -> game.guess("woord"));
    }
}