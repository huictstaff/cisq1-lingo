package nl.hu.cisq1.lingo.application;
import nl.hu.cisq1.lingo.data.SpringGameRepository;
import nl.hu.cisq1.lingo.domain.Game;
import nl.hu.cisq1.lingo.domain.exception.ForbiddenGuessException;
import nl.hu.cisq1.lingo.domain.exception.ForbiddenRoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TrainerServiceTest {
    @Test
    @DisplayName("Game is made")
    void startsNewGame(){
        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
        Game game = mock(Game.class);
        when(gameRepository.save(Mockito.any(Game.class))).thenReturn(game);
        WordService wordService = mock(WordService.class);
        when(wordService.provideRandomWord(5)).thenReturn("appel");

        TrainerService trainerservice = new TrainerService(wordService, gameRepository);

        assertEquals(trainerservice.startNewGame(), game);
    }
    @Test
    @DisplayName("Game is not found give exception")
    void gameNotFound(){
        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
        WordService wordService = mock(WordService.class);
        when(gameRepository.save(Mockito.any(Game.class))).thenReturn(null);

        TrainerService trainerservice = new TrainerService(wordService, gameRepository);

        assertThrows(ForbiddenGuessException.class, () -> trainerservice.getGame(1L));
    }

    @Test
    @DisplayName("New round is created")
    void startNewRound(){
        SpringGameRepository mockedGameRepository = mock(SpringGameRepository.class);
        WordService wordService = mock(WordService.class);

        Game game = new Game("appel");
        game.getLastRound().doGuess("appel");

        when(mockedGameRepository.findById(1L))
                .thenReturn(java.util.Optional.of(game));

        when(wordService.provideRandomWord(5))
                .thenReturn("brood");

        when(mockedGameRepository.save(Mockito.any(Game.class))).thenAnswer(i -> i.getArguments()[0]);

        TrainerService trainerservice = new TrainerService(wordService, mockedGameRepository);
        assertEquals(2, trainerservice.startNewRound(1L).getRounds().size());

    }

    @Test
    @DisplayName("New round throws error if previous is running or failed")
    void startNewRoundNotAccepted(){
        SpringGameRepository mockedGameRepository = mock(SpringGameRepository.class);
        WordService wordService = mock(WordService.class);
        Game game = new Game("appel");
        when(mockedGameRepository.findById(1L))
                .thenReturn(java.util.Optional.of(game));

        when(wordService.provideRandomWord(5))
                .thenReturn("brood");

        when(mockedGameRepository.save(Mockito.any(Game.class))).thenAnswer(i -> i.getArguments()[0]);

        TrainerService trainerservice = new TrainerService(wordService, mockedGameRepository);

        assertThrows(ForbiddenRoundException.class, () -> trainerservice.startNewRound(1L));
    }

    @Test
    @DisplayName("Do guess gives in round gave same feedback as doguess in TrainerService")
    void doGuess(){
        SpringGameRepository mockedGameRepository = mock(SpringGameRepository.class);
        WordService wordService = mock(WordService.class);
        Game game = new Game("appel");
        when(mockedGameRepository.findById(1L))
                .thenReturn(java.util.Optional.of(game));

        when(mockedGameRepository.save(Mockito.any(Game.class))).thenAnswer(i -> i.getArguments()[0]);

        TrainerService trainerservice = new TrainerService(wordService, mockedGameRepository);

        assertEquals(game.getLastRound().doGuess("brood"),trainerservice.doGuess(1, "brood").getLastRound().getFeedback());
    }

    @Test
    @DisplayName("Do guess gives error if there have been to many attempts.")
    void doGuessToMany(){
        SpringGameRepository mockedGameRepository = mock(SpringGameRepository.class);
        WordService wordService = mock(WordService.class);
        Game game = new Game("appel");
        when(mockedGameRepository.findById(1L))
                .thenReturn(java.util.Optional.of(game));

        when(mockedGameRepository.save(Mockito.any(Game.class))).thenAnswer(i -> i.getArguments()[0]);

        game.getLastRound().doGuess("brood");
        game.getLastRound().doGuess("braad");
        game.getLastRound().doGuess("bruud");
        game.getLastRound().doGuess("broed");
        game.getLastRound().doGuess("breed");

        TrainerService trainerservice = new TrainerService(wordService, mockedGameRepository);

        assertThrows(ForbiddenGuessException.class, () ->trainerservice.doGuess(1, "brood"));
    }

}
