package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.Gamestate;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.*;
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

        Word word = new Word("BROOD");

        when(springGameRepository.getById(anyInt())).thenReturn(new Game(word));

        when(wordService.provideRandomWord(anyInt())).thenReturn("BROOD");

        /** Start the game */
        gameService.startNewGame();

        /** Make a guess */
        Game game = gameService.guess(1, "BOTER");
        assertEquals(Gamestate.ACTIVE, game.getGamestate());
        assertEquals(0, game.getScore());
        assertEquals(1, game.getRounds().size());
    }

    @Test
    @DisplayName("Guessing the word correctly in two tries")
    void guessCorrectly() {
        WordService wordService = mock(WordService.class);
        SpringGameRepository springGameRepository = mock(SpringGameRepository.class);
        GameService gameService = new GameService(wordService, springGameRepository);

        Word word = new Word("BROOD");

        when(springGameRepository.getById(anyInt())).thenReturn(new Game(word));

        when(wordService.provideRandomWord(anyInt())).thenReturn("BROOD");

        /** Start the game */
        gameService.startNewGame();

        /** Make a guess */
        Game game = gameService.guess(1, "BOTER");
        assertEquals(Gamestate.ACTIVE, game.getGamestate());
        assertEquals(0, game.getScore());
        assertEquals(1, game.getRounds().size());
        assertEquals(game.getRoundFeedback().get(0).getMarks(), List.of(CORRECT, PRESENT, ABSENT, ABSENT, PRESENT));

        /** Make a correct guess */
        game = gameService.guess(1, "BROOD");
        assertEquals(Gamestate.WAITING, game.getGamestate());
        assertEquals(20, game.getScore());
        assertEquals(2, game.getRoundFeedback().size());
        assertEquals(game.getRoundFeedback().get(1).getMarks(), List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT));
    }

    @Test
    @DisplayName("Getting the right score")
    void getCorrectScore() {

    }

    @Test
    @DisplayName("starting a new round")
    void startNewRound() {
        WordService wordService = mock(WordService.class);
        SpringGameRepository springGameRepository = mock(SpringGameRepository.class);
        GameService gameService = new GameService(wordService, springGameRepository);

        Game game = new Game(new Word("BROOD"));
        game.guess("BROOD");

        when(springGameRepository.getById(anyInt())).thenReturn(game);
        when(wordService.provideRandomWord(6)).thenReturn("BRODEN");

        assertEquals(Gamestate.WAITING, game.getGamestate());

        gameService.startNewRound(1);

        assertEquals(Gamestate.ACTIVE, game.getGamestate());
        assertEquals(2, game.getRounds().size());

    }

    @Test
    void getGameProgress() {
    }

    @Test
    void getGame() {
    }
}