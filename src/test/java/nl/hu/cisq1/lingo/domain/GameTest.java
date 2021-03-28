package nl.hu.cisq1.lingo.domain;

import nl.hu.cisq1.lingo.domain.Enums.GameState;
import nl.hu.cisq1.lingo.domain.exception.ForbiddenRoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {
    static Stream<Arguments> provideCaclulateExamples() {
        //Alles moet 1je lager zijn
        return Stream.of(
                Arguments.of(1,5), //1 is er al dus eigenlijk is het 2.
                Arguments.of(17,6),
                Arguments.of(10,5),
                Arguments.of(6,7)

        );
    }

    @ParameterizedTest
    @MethodSource("provideCaclulateExamples")
    @DisplayName("Test if calculate function works.")
    void calculateWordLengthTest(int roundsToCreate, int answer){
        Game game = new Game("Testing");
        game.getLastRound().doGuess("Testing");
        for(int i = 1; i < roundsToCreate; i++){
            game.startNewRound("testonetwo");
            game.getLastRound().doGuess("testonetwo");
        }
        System.out.println("The size of rounds = " + game.getRounds().size() + "Expected: " + roundsToCreate);
        assertEquals(answer, game.calculateWordLength());
    }

    @Test
    @DisplayName("Test creating new round fails if the word of the last round isn't guessed")
    void failToCreateNewRound(){
        Game game = new Game("TestOneTwo");
        game.getLastRound().doGuess("TestOneTwo");
        game.startNewRound("testOneTwo");
        assertThrows(ForbiddenRoundException.class,
                () -> game.startNewRound("testOneTwo"));
    }

    @Test
    @DisplayName("Test creating new round fails if State of game is GAME.ELIMINATED")
    void failToCreateNewRoundifStateisEliminated(){
        Game game = new Game("TestOneTwo");
        game.setGameState(GameState.ELIMINATED);
        assertThrows(ForbiddenRoundException.class,
                () -> game.startNewRound("testOneTwo"));
        assertEquals(1, game.getRounds().size());
    }

}
