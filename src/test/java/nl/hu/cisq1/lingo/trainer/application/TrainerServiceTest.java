package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.application.exception.GameNotFoundException;
import nl.hu.cisq1.lingo.trainer.data.GameEntity;
import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.GameStatus;
import nl.hu.cisq1.lingo.trainer.domain.exception.LostGameException;
import nl.hu.cisq1.lingo.trainer.domain.exception.RoundAlreadyStartedException;
import nl.hu.cisq1.lingo.trainer.domain.exception.RoundNotStartedException;
import nl.hu.cisq1.lingo.trainer.presentation.dto.ProgressDTO;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TrainerServiceTest {
    private WordService wordService;
    private SpringGameRepository repository;
    private TrainerService trainerService;

    @BeforeEach
    public void before() {
        // Test game
        Game game = new Game();
        Optional<GameEntity> gameEntityOptional = Optional.of(new GameEntity(game));

        // Mocks
        wordService = mock(WordService.class);
        when(wordService.provideRandomWord(5)).thenReturn("apple");
        when(wordService.provideRandomWord(6)).thenReturn("apples");
        when(wordService.provideRandomWord(7)).thenReturn("appless");
        repository = mock(SpringGameRepository.class);
        when(repository.findById(0L)).thenReturn(gameEntityOptional);

        // Trainer
        trainerService = new TrainerService(wordService, repository);
        trainerService.startNewGame();
    }

    @Test
    @DisplayName("throws exception if game is not found")
    public void gameNotFoundException() {
        assertThrows(GameNotFoundException.class, () -> trainerService.startNewRound(1L));
        assertThrows(GameNotFoundException.class, () -> trainerService.guessWord(1L, ""));
        assertThrows(GameNotFoundException.class, () -> trainerService.getProgress(1L));
    }

    @Test
    @DisplayName("throws exception if trying to guess while round is not started yet")
    public void roundNotStartedException() {
        assertThrows(RoundNotStartedException.class, () -> trainerService.guessWord(0L, ""));
    }

    @Test
    @DisplayName("throws exception if trying to start new round while round is already started")
    public void roundAlreadyStartedException() {
        trainerService.startNewRound(0L);
        assertThrows(RoundAlreadyStartedException.class, () -> trainerService.startNewRound(0L));
    }

    @Test
    @DisplayName("throws exception if trying to guess while game is lost")
    public void lostGameException() {
        // Lose on purpose
        trainerService.startNewRound(0L);
        trainerService.guessWord(0L, "pizza");
        trainerService.guessWord(0L, "pizza");
        trainerService.guessWord(0L, "pizza");
        trainerService.guessWord(0L, "pizza");
        trainerService.guessWord(0L, "pizza");

        // Must throw exception
        assertThrows(LostGameException.class, () -> trainerService.guessWord(0L, "pizza"));
    }

    @Test
    @DisplayName("initial progress must be correct")
    public void initialProgressCorrect() {
        // Retrieve progress
        ProgressDTO progressDTO = trainerService.getProgress(0L);

        // Asserts
        assertEquals(progressDTO.gameStatus, GameStatus.WAITING);
        assertEquals(progressDTO.currentRound, 0);
        assertEquals(progressDTO.score, 0);
        assertEquals(progressDTO.feedbacks.size(), 0);
    }

    @Test
    @DisplayName("new round progress must be correct")
    public void roundProgressCorrect() {
        // Retrieve progress
        trainerService.startNewRound(0L);
        ProgressDTO progressDTO = trainerService.getProgress(0L);

        // Asserts
        assertEquals(progressDTO.gameStatus, GameStatus.PLAYING);
        assertEquals(progressDTO.currentRound, 1);
        assertEquals(progressDTO.score, 0);
        assertEquals(progressDTO.feedbacks.size(), 1);
    }

    @Test
    @DisplayName("progress after guess must be correct")
    public void guessProgressCorrect() {
        // Guess the word
        trainerService.startNewRound(0L);
        ProgressDTO progressDTO = trainerService.guessWord(0L, "apple");

        // Asserts
        assertEquals(progressDTO.gameStatus, GameStatus.WAITING);
        assertEquals(progressDTO.currentRound, 1);
        assertEquals(progressDTO.score, 25);
        assertEquals(progressDTO.feedbacks.size(), 2);
    }
}