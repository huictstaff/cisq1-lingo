package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.data.Game;
import nl.hu.cisq1.lingo.trainer.data.GameRepository;
import nl.hu.cisq1.lingo.trainer.domain.LingoGame;
import nl.hu.cisq1.lingo.trainer.exception.GameNotFoundException;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LingoServiceTest {

    @Test
    @DisplayName("Succesfully creating a game")
    void startOrContinueGame() {
        WordService wordService = mock(WordService.class);
        when(wordService.provideRandomWord(anyInt())).thenReturn("appel");

        GameRepository gameRepository = mock(GameRepository.class);

        LingoService lingoService = new LingoService(wordService, gameRepository);

        assertDoesNotThrow(lingoService::startGame);
        assertNotNull(lingoService.startGame());
    }

    @Test
    @DisplayName("Making a guess when no game is started throws GameNotFoundException")
    void makeGuessWithoutExistingGame() {
        WordService wordService = mock(WordService.class);
        GameRepository gameRepository = mock(GameRepository.class);
        LingoService lingoService = new LingoService(wordService, gameRepository);

        assertThrows(GameNotFoundException.class, () -> lingoService.makeGuess(anyString()));
    }

    @Test
    @DisplayName("Succesfully making a wrong guess")
    void makeWrongGuess() {
        LingoGame game = new LingoGame();
        game.newRound("appel");

        WordService wordService = mock(WordService.class);
        when(wordService.wordExistsByValue(anyString())).thenReturn(true);

        GameRepository gameRepository = mock(GameRepository.class);
        when(gameRepository.findTopByGameDoneOrderByIdDesc(anyBoolean())).thenReturn(Optional.of(new Game(game, false)));
        LingoService lingoService = new LingoService(wordService, gameRepository);

        assertDoesNotThrow(() -> lingoService.makeGuess("petje"));
        assertNotNull(lingoService.makeGuess("petje"));
    }

    @Test
    @DisplayName("Succesfully making a good guess")
    void makeGoodGuess() {
        LingoGame game = new LingoGame();
        game.newRound("appel");

        WordService wordService = mock(WordService.class);
        when(wordService.wordExistsByValue(anyString())).thenReturn(true);
        when(wordService.provideRandomWord(anyInt())).thenReturn("banaan");

        GameRepository gameRepository = mock(GameRepository.class);
        when(gameRepository.findTopByGameDoneOrderByIdDesc(anyBoolean())).thenReturn(Optional.of(new Game(game, false)));
        LingoService lingoService = new LingoService(wordService, gameRepository);

        assertDoesNotThrow(() -> lingoService.makeGuess("appel"));
    }

    @Test
    @DisplayName("Succesfully making 5 wrong guesses")
    void makeTooManyWrongGuess() {
        LingoGame game = new LingoGame();
        game.newRound("appel");

        WordService wordService = mock(WordService.class);
        when(wordService.wordExistsByValue(anyString())).thenReturn(true);

        GameRepository gameRepository = mock(GameRepository.class);
        when(gameRepository.findTopByGameDoneOrderByIdDesc(anyBoolean())).thenReturn(Optional.of(new Game(game, false)));
        LingoService lingoService = new LingoService(wordService, gameRepository);

        lingoService.makeGuess("petje");
        lingoService.makeGuess("petje");
        lingoService.makeGuess("petje");
        lingoService.makeGuess("petje");
        assertDoesNotThrow(() -> lingoService.makeGuess("petje"));
    }
}
