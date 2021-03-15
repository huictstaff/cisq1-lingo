package trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    public static Stream<Arguments> provideWords() {
        return Stream.of(
                Arguments.of("woord"),
                Arguments.of("klein")
        );
    }

    @ParameterizedTest
    @MethodSource("provideWords")
    @DisplayName("round is made when it appears last in the rounds list in the game object")
    public void roundMade(String word) {
        Game game = new Game();
        Round round = game.makeRound(word);
        List<Round> rounds = game.getRounds();
        assertEquals(rounds.get(rounds.size() - 1), round);
    }

}