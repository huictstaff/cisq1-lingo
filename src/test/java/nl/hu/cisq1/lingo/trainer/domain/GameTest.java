package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.ActiveRoundException;
import nl.hu.cisq1.lingo.trainer.domain.exception.GameLostException;
import nl.hu.cisq1.lingo.trainer.domain.exception.NoActiveRoundException;
import nl.hu.cisq1.lingo.trainer.domain.exception.RoundWonException;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    @DisplayName("Constructor should not fail with no arguments")
    void emptyConstructor() {
        assertDoesNotThrow((ThrowingSupplier<Game>) Game::new);
    }

    @Test
    @DisplayName("Constructor should not fail with only id argument")
    void idConstructor() {
        assertDoesNotThrow(() -> new Game(1));
    }

    @Test
    @DisplayName("new Round should throw ActiveRoundException when there's still an existing round")
    void activeRoundException() {
        Game game = new Game();
        game.newRound("worsten");
        assertThrows(ActiveRoundException.class, () -> game.newRound("worsten"));

    }

    @Test
    @DisplayName("After 5th guess, game should throw GameLostException when gamestate is LOST")
    void gameLostException() {
        Game game = new Game();
        game.newRound("kerels");
        game.makeGuess("kaasje");
        game.makeGuess("kaasje");
        game.makeGuess("kaasje");
        game.makeGuess("kaasje");
        assertThrows(GameLostException.class, () -> game.makeGuess("kaasje"));
    }

    @Test
    @DisplayName("New round after Game Lost should give GameLostException")
    void gameLostExceptionWhenNewRound() {
        Game game = new Game();
        game.newRound("kerels");
        game.makeGuess("kaasje");
        game.makeGuess("kaasje");
        game.makeGuess("kaasje");
        game.makeGuess("kaasje");
        try {
            game.makeGuess("kaasje");
        } catch (Exception e) {
            assertThrows(GameLostException.class, () -> game.newRound("biertje"));
        }
    }

    @Test
    @DisplayName("New Guess after Game Lost should give GameLostException")
    void gameLostExceptionWhenNewGuess() {
        Game game = new Game();
        game.newRound("kerels");
        game.makeGuess("kaasje");
        game.makeGuess("kaasje");
        game.makeGuess("kaasje");
        game.makeGuess("kaasje");
        try {
            game.makeGuess("kaasje");
        } catch (Exception e) {
            assertThrows(GameLostException.class, () -> game.makeGuess("biertje"));
        }
    }

    @Test
    @DisplayName("New Guess after Game Won should give RoundWonException")
    void RoundWonExceptionWhenNewGuess() {
        Game game = new Game();
        game.newRound("kerels");
        game.makeGuess("kaasje");
        game.makeGuess("kaasje");
        game.makeGuess("kaasje");
        game.makeGuess("kaasje");
        game.makeGuess("kerels");
        assertThrows(RoundWonException.class, () -> game.makeGuess("trytryt"));
    }

    @Test
    @DisplayName("NoActiveRoundException when no active is active")
    void provideNextLengthWordNoActiveRound() {
        Game game = new Game();
        assertThrows(NoActiveRoundException.class, game::provideNextLenghtWord);
    }

    @Test
    @DisplayName("endRound ends the round")
    void endRound() {
        Game game = new Game();
        game.newRound("woordje");
        game.makeGuess("weerdjo");
        game.makeGuess("weerdjo");
        game.makeGuess("woordje");
        game.endRound();
        assertDoesNotThrow(() -> game.newRound("weerjo"));
    }

    @Test
    @DisplayName("Make guess throws exception when game is lost")
    void GuessingImpossibleAfterGameLost() {
        Game game = new Game();
        game.newRound("nootjes");
        game.makeGuess("neetjos");
        game.makeGuess("neetjos");
        game.makeGuess("neetjos");
        game.makeGuess("neetjos");
        try {
            game.makeGuess("neetjos");
        } catch (Exception e) {
            assertThrows(GameLostException.class, () -> game.makeGuess("nachoss"));
        } finally {
            assertThrows(GameLostException.class, () -> game.makeGuess("nachoss"));
        }
    }

    @Test
    @DisplayName("Make guess throws exception when game is won")
    void GuessingImpossibleAfterGameWon() {
        Game game = new Game();
        game.newRound("nootjes");
        game.makeGuess("neetjos");
        game.makeGuess("neetjos");
        game.makeGuess("neetjos");
        game.makeGuess("neetjos");
        try {
            game.makeGuess("nootjes");
        } catch (Exception e) {
            assertThrows(RoundWonException.class, () -> game.makeGuess("nootjea"));
        } finally {
            assertThrows(RoundWonException.class, () -> game.makeGuess("nootjea"));
        }
    }

    @Test
    @DisplayName("Id getter")
    void getId() {
        Game game = new Game(1);
        assertEquals(1, game.getId());
    }

    @ParameterizedTest
    @DisplayName("calculate score gives correct result")
    @MethodSource("provideRoundsToCalculate")
    void calculateScore(int expected, int actual) {
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @DisplayName("Lenght of next word")
    @MethodSource("provideNextLenghtTest")
    void provideNextLenghtWord(int expected, int actual) {
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideNextLenghtTest() {
        Game game = new Game();
        game.newRound("woord");
        Game game1 = new Game();
        game1.newRound("woordj");
        Game game2 = new Game();
        game2.newRound("woordje");
        return Stream.of(
                Arguments.of(6, game.provideNextLenghtWord()),
                Arguments.of(7, game1.provideNextLenghtWord()),
                Arguments.of(5, game2.provideNextLenghtWord())
        );
    }

    private static Stream<Arguments> provideRoundsToCalculate() {
        Game game = new Game();
        game.newRound(new Word("woord").getValue());
        game.makeGuess("woord");
        game.endRound();

        Game game1 = new Game();
        game1.newRound("woord");
        game1.makeGuess("woord");
        game1.endRound();
        game1.newRound("woordj");
        game1.makeGuess("woordj");
        game1.endRound();

        Game game2 = new Game();
        game2.newRound("woord");
        game2.makeGuess("woord");
        game2.endRound();
        game2.newRound("woordj");
        game2.makeGuess("woordj");
        game2.endRound();
        game2.newRound("woordj");
        game2.makeGuess("woordz");
        game2.makeGuess("woordz");
        game2.makeGuess("woordz");
        game2.makeGuess("woordz");
        try {
            game2.makeGuess("woordz");
        } catch (Exception ignored) {
        }
        game2.endRound();

        Game game3 = new Game();
        game3.newRound("woord");
        game3.makeGuess("woord");
        game3.endRound();
        game3.newRound("woordj");
        game3.makeGuess("woordj");
        game3.endRound();
        game3.newRound("woordje");
        game3.makeGuess("woordje");
        game3.endRound();

        Game game4 = new Game();
        game4.newRound("woord");
        game4.makeGuess("woord");
        game4.endRound();
        game4.newRound("woordj");
        game4.endRound();
        game4.newRound("woordje");
        game4.makeGuess("woordjz");
        game4.makeGuess("woordjz");
        game4.makeGuess("woordjz");
        game4.makeGuess("woordjz");
        game4.makeGuess("woordje");
        game4.endRound();

        return Stream.of(
                Arguments.of(25, game.getPoints()),
                Arguments.of(50, game1.getPoints()),
                Arguments.of(50, game2.getPoints()),
                Arguments.of(75, game3.getPoints()),
                Arguments.of(30, game4.getPoints()));
    }
}
