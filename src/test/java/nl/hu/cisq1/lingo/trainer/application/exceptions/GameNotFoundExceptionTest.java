package nl.hu.cisq1.lingo.trainer.application.exceptions;

import nl.hu.cisq1.lingo.trainer.application.GameService;
import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameNotFoundExceptionTest {
    private GameService gameService;

    @BeforeEach
    void populateVariables() {
        Game game = new Game();

        WordService wordService = mock(WordService.class);
        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
        this.gameService = new GameService(gameRepository, wordService);

        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));
    }

    @Test
    @DisplayName("getGame on an existing game should return that game")
    void wrongIdGetGame() {
        assertThrows(GameNotFoundException.class, () -> gameService.getGame(2L));
    }

    @Test
    @DisplayName("using a wrong id should return null")
    void wrongIdGuess() {
        assertThrows(GameNotFoundException.class, () -> gameService.guess(2L, "woord"));
    }

    @Test
    @DisplayName("using a wrong id should return null")
    void wrongIdNewRound() {
        assertThrows(GameNotFoundException.class, () -> gameService.newRound(2L));
    }
}