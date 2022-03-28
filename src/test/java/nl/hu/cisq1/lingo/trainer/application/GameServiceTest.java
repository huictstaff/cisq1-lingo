package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.Gamestate;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameServiceTest {

    @Test
    @DisplayName("Starting a new game")
    void startNewGame() {
        WordService wordService = mock(WordService.class);
        SpringGameRepository springGameRepository = mock(SpringGameRepository.class);
        GameService gameService = new GameService(wordService, springGameRepository);

        when(wordService.provideRandomWord(anyInt())).thenReturn("BROOD");

        Game game = gameService.startNewGame();

        assertEquals(Gamestate.ACTIVE, game.getGamestate());
        assertEquals(1, game.getRounds().size());
        assertEquals(0, game.getScore());
    }

    @Test
    void guess() {
        WordService wordService = mock(WordService.class);
        SpringGameRepository springGameRepository = mock(SpringGameRepository.class);
        GameService gameService = new GameService(wordService, springGameRepository);

        when(wordService.provideRandomWord(anyInt())).thenReturn("BROOD");

//        Game game = gameService.guess(1, "BROOD");
        assertEquals(true, gameService.guess(1, "BROOD"));
    }

    @Test
    void startNewRound() {
    }

    @Test
    void getGameProgress() {
    }

    @Test
    void getGame() {
    }
}