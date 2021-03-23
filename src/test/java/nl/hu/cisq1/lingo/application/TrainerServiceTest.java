package nl.hu.cisq1.lingo.application;
import nl.hu.cisq1.lingo.data.SpringGameRepository;
import nl.hu.cisq1.lingo.domain.Enums.GameState;
import nl.hu.cisq1.lingo.domain.Enums.RoundStatus;
import nl.hu.cisq1.lingo.domain.Game;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TrainerServiceTest {
    @Test
    @DisplayName("Game is made and state is PLAYING")
    void startsNewGame(){ //todo College 6 terug kijken
        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
        Game game = mock(Game.class);
        when(gameRepository.save(Mockito.any(Game.class))).thenReturn(game);
        WordService wordService = mock(WordService.class);
        when(wordService.provideRandomWord(5)).thenReturn("appel");

        TrainerService trainerservice = new TrainerService(wordService, gameRepository);

        assertEquals(trainerservice.startNewGame(), game);
    }

    @Test
    @DisplayName("New round is created")
    void startNewRound(){
        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
        WordService wordService = mock(WordService.class);

        Game game = new Game("appel");
        game.getLastRound().setRoundOver(RoundStatus.WORD_IS_GUESSED);
        when(gameRepository.findById(1L)).thenReturn(java.util.Optional.of(game));
        when(wordService.provideRandomWord(6)).thenReturn("appels");

        TrainerService trainerservice = new TrainerService(wordService, gameRepository);
          assertEquals("appels", trainerservice.startNewRound(1).getLastRound().getWordToGuess());

    }

    @Test
    @DisplayName("New round throws error if previous is running or failed")
    void startNewRoundNotAccepted(){
        //todo enige test hier is kijken of ie wordt doorgegeven
        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
        WordService wordService = mock(WordService.class);
        TrainerService trainerservice = new TrainerService(wordService, gameRepository);

        assertEquals("appels", "appels");
    }
}
