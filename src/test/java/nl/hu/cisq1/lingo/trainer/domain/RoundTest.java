package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoundTest {

    @Test
    @DisplayName("when the game has just started then this function will be called and the first letter will be shown")
    void firstHint() {
        Round round = new Round("baard");
        assertEquals(round.giveHint(), "b....");
    }

    @Test
    @DisplayName("when the first guess has been made")
    void firstguess() {
        Round round = new Round("baard");
        round.guessing("board");
        assertEquals(round.giveHint(), "b.ard");
    }

    @Test
    @DisplayName("a round has been won")
    void RoundHasBeenWon() {
        Round round = new Round("baard");
        round.guessing("baard");
        assertEquals(round.getStatus(), GameState.WON);
    }

    @Test
    @DisplayName("the usser is still playing a round")
    void roundStatusIsPlaying() {
        Round round = new Round("baard");
        round.guessing("board");
        assertEquals(round.getStatus(), GameState.PLAYING);
    }


    @Test
    @DisplayName("a round has been Lost, and the word has not been guessed ")
    void RoundHasBeenLost() {
        Round round = new Round("baard");
        round.guessing("bakke");
        round.guessing("balde");
        round.guessing("bored");
        round.guessing("borak");
        round.guessing("baner");
        assertEquals(round.getStatus(), GameState.ELIMINATED);
    }

}