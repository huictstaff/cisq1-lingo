package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    @DisplayName("Making a game, this test should show if a Game is made")
    public void createNewGame(){
        Game game = new Game();
        assertEquals(game.toString(),"Game is currently ongoing, score = 0, amount of rounds = 0, the round words: ");
    }

    @Test
    @DisplayName("Making a round, this test should show if a Game is made with a new round")
    public void createNewRound(){
        Game game = new Game();
        Round round1 = game.createRound("Grond");
        assertEquals(game.toString(),"Game is currently ongoing, score = 0, amount of rounds = 1, the round words: grond");
    }

    //TODO: ask HUGO how i can test this or if i can at all (assertThrows?)
//    @Test
//    @DisplayName("Making a round, this test should show if a Game is made with a new round")
//    public void creatingNewRoundTheGameOver(){
//        Game game = new Game();
//        Round round1 = game.createRound("Grond");
//        round1.inputWord("Grond");
//        game.stopGame();
//        Round round2 = game.createRound("Grond");
//        game.calculateScore();
//        assertEquals(round2 ,"");
//    }

    @Test
    @DisplayName("Making a round, this test should show if a Game is made with a new round")
    public void calculatingGameScore(){
        Game game = new Game();
        Round round1 = game.createRound("Grond");
        round1.inputWord("Grond");
        Round round2 = game.createRound("Grond");
        round2.inputWord("Grond");
        Round round3 = game.createRound("Grond");
        round3.inputWord("Grond");
        Round round4 = game.createRound("Grond");
        game.calculateScore();
        assertEquals(game.getScore(),300);
    }

    @ParameterizedTest
    @MethodSource("provideGameRounds")
    @DisplayName("Get the latest word length, This test should be give guess word")
    public void getWordLength(String woord, int length){
        Game game = new Game();
        game.createRound(woord);
        assertEquals(game.getLastRoundsWordLength(), length);
    }

    public static Stream<Arguments> provideGameRounds() {
        return Stream.of(
                Arguments.of("Zacht", 5),
                Arguments.of("Zaakje", 6),
                Arguments.of("zachter", 7));
    }
}