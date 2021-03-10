package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


class GameTest {

    private Game game;

    @BeforeEach
    @DisplayName("init")
    void init() {
        game = new Game();
    }

    @Test
    @DisplayName("new round can only be started if there is no open round")
    void roundMadeWithNoOpenRounds() {
        // Arrange
        Word wordToGuess = new Word("BAARD");

        // Act
        game.startNewRound(wordToGuess);

        // Assert
        Round round = new Round(wordToGuess);
        assertEquals(round,game.getRounds().get(0));
    }

    @Test
    @DisplayName("new round can not be started when there is already an open round")
    void roundMadeWithOpenRounds() {
        // Arrange
        Word wordToGuess = new Word("BAARD");
        game.startNewRound(wordToGuess);

        // Act / Assert
        assertThrows(IllegalStateException.class, () -> {
            game.startNewRound(wordToGuess);
        });

    }

    @Test
    @DisplayName("new round can not be started when the game is over")
    void newRoundWhenGameIsOver() {
        // Arrange
        Word wordToGuess = new Word("BAARD");
        game.startNewRound(wordToGuess);
        game.guess("BAREN");
        game.guess("BAREN");
        game.guess("BAREN");
        game.guess("BAREN");
        game.guess("BAREN");

        // Act / Assert
        Word wordToGuess2 = new Word("BLOEM");
        assertThrows(IllegalStateException.class, () -> {
            game.startNewRound(wordToGuess2);
        });

    }

    @Test
    @DisplayName("guess can not be made if the player is eliminated")
    void guessWhenGameOver() {
        // Arrange
        Word wordToGuess = new Word("BAARD");
        game.startNewRound(wordToGuess);
        game.guess("BAREN");
        game.guess("BAREN");
        game.guess("BAREN");
        game.guess("BAREN");
        game.guess("BAREN");

        // Act / Assert
        assertThrows(IllegalStateException.class, () -> {
            game.guess("BAREN");
        });
    }

    @Test
    @DisplayName("guess can not be made if the game has no open round")
    void guessWhenNoRound() {
        // Arrange

        // Act / Assert
        assertThrows(IllegalStateException.class, () -> {
            game.guess("BAREN");
        });

    }

    @Test
    @DisplayName("score is added when the word is guessed")
    void addScoreWhenWordIsGuessed() {
        // Arrange
        Word wordToGuess = new Word("BAARD");
        game.startNewRound(wordToGuess);

        // Act
        game.guess("BAARD");

        // Assert
        assertEquals(25,game.getProgress().getScore());

    }

    @Test
    @DisplayName("progress is cleared when a new round is started")
    void progressIsCleared() {
        // Arrange
        Word wordToGuess = new Word("BAARD");
        game.startNewRound(wordToGuess);

        // Act
        game.guess("BAARD");

        Word wordToGuess2 = new Word("PLAAG");
        game.startNewRound(wordToGuess2);

        // Assert
        assertNull(game.getProgress().getLastFeedback());

    }

    @Test
    @DisplayName("progress is not cleared when the round is still going")
    void progressIsNotCleared() {
        // Arrange
        Word wordToGuess = new Word("BAARD");
        game.startNewRound(wordToGuess);

        // Act
        game.guess("BAREN");

        // Assert
        assertEquals("BAREN", game.getProgress().getLastFeedback().getAttempt());
    }

}