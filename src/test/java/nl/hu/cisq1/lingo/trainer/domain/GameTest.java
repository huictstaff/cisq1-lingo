package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.enums.GameStatus;
import nl.hu.cisq1.lingo.trainer.domain.exception.GameException;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class GameTest {
    private static Word word1;
    private static Word word2;
    private Game game;
    private Round round1;
    private Round round2;

    @BeforeAll
    public static void  setUpTGW() {
        word1 = new Word("woord");
        word2 = new Word("test");
    }

    @BeforeEach
    public void setUpGame() {
        game = new Game();
        round1 = new Round(word1);
        round2 = new Round(word2);
    }


    private static Stream<Arguments> provideRoundsWithDifferentResults() {
        return Stream.of(
                Arguments.of(word1, 1),
                Arguments.of(word1, 2),
                Arguments.of(word1, 3),
                Arguments.of(word1, 4)
        );
    }


    @ParameterizedTest
    @MethodSource("provideRoundsWithDifferentResults")
    public void scoreTest(Word word, int triesNum) throws Exception {
        Round round = Round.withAttempts(word, triesNum);
        assertTrue(round.isGuessed());
        game.startGame(round);
        assertEquals(5 * (5 - triesNum) + 5, game.getScore());
    }



    @Test
    @DisplayName("starting a new game")
    public void startGameHappyFlow() throws Exception {
        assertEquals(GameStatus.WAITING_FOR_ROUND, game.getGameStatus());
        assertDoesNotThrow(() -> game.startGame(round1));
        assertTrue(round1.roundIsRunning());
        assertEquals(GameStatus.PLAYING, game.getGameStatus());
    }

    @Test
    @DisplayName("exception is thrown one tries to start an already started game")
    public void startAlraedyStratedGame() throws Exception {
        game.startGame(round1);
        assertThrows(GameException.class, () -> game.startGame(round2));
    }


    @Test
    public void nextRoundNotStartedGame() throws Exception {
        assertEquals(GameStatus.WAITING_FOR_ROUND, game.getGameStatus());
        assertThrows(GameException.class, () -> game.nextRound(round1));
    }

    @Test
    public void nextRoundEndedGame() throws Exception {
        game.startGame(round1);
        game.eliminateGame();
        assertEquals(GameStatus.ELIMINATED, game.getGameStatus());
        assertThrows(GameException.class,() -> game.nextRound(round2));
    }


    @Test
    public void switchStatusToWaitingIfRoundEnded() throws Exception {
        game.startGame(round1);
        assertEquals(GameStatus.PLAYING, game.getGameStatus());
        round1.terminate();
        assertFalse(game.isLastRoundStillPlaying());
        assertEquals(GameStatus.WAITING_FOR_ROUND, game.getGameStatus());
    }

    @Test
    public void nextRoundWithARunningRound() throws Exception {
        game.startGame(round1);
        assertEquals(GameStatus.PLAYING, game.getGameStatus());
        assertEquals(1, game.getRounds().size());
        assertThrows(GameException.class, () -> game.nextRound(round2));
    }
}