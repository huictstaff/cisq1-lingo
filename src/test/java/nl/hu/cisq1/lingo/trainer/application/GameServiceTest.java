package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.application.dto.GameDTO;
import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.*;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameServiceTest {
    private Game game;
    private GameService gameService;

    @BeforeEach
    void populateVariables() {
        String wordToGuess = "woord";
        Feedback initialFeedback = Feedback.initialFeedback(wordToGuess);
        Hint initialHint = Hint.initialHint(initialFeedback.getMarks(), 'w', 5);

        List<Feedback> feedbackList = new ArrayList<>();
        feedbackList.add(initialFeedback);

        List<Hint> hints = new ArrayList<>();
        hints.add(initialHint);

        Round round = new Round(2L, wordToGuess, State.IN_PROGRESS, 0, feedbackList,
                hints, this.game);

        List<Round> rounds = new ArrayList<>();
        rounds.add(round);

        this.game = new Game(1L, 0, rounds);

        WordService wordService = mock(WordService.class);
        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
        this.gameService = new GameService(gameRepository, wordService);

        when(wordService.provideRandomWord(anyInt())).thenReturn(wordToGuess);
        when(gameRepository.save(any())).thenReturn(this.game);
        when(gameRepository.findById(1L)).thenReturn(Optional.of(this.game));
    }

    @Test
    @DisplayName("createGame should return a gameDTO version of the game")
    void createGame() {
        GameDTO gameDTO = gameService.createGame();

        assertEquals(new GameDTO(this.game), gameDTO);
    }

    @Test
    @DisplayName("getGame on an existing game should return that game")
    void getGame() {
        GameDTO gameDTO = gameService.getGame(1L);

        assertNotNull(gameDTO);
    }

    @Test
    @DisplayName("guessing should return a GameDTO object that has hints for the guess")
    void guess() {
        GameDTO gameDTO = gameService.guess(1L, "worod");

        assertTrue(gameDTO.getRounds().get(0).getHints().size() > 1);
    }

    @Test
    void newRound() {
        this.game.getActiveRound().setState(State.LOST);

        GameDTO gameDTO = gameService.newRound(1L);

        assertEquals(2, gameDTO.getRounds().size());
    }
}