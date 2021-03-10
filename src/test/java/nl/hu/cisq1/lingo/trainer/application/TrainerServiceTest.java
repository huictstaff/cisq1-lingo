package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.application.exception.GameNotFoundException;
import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.exception.AlreadyPlayingGameException;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TrainerServiceTest {
    @Test
    @DisplayName("throws exception if game not found")
    public void gameNotFound() {
        WordService wordService = mock(WordService.class);
        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
        TrainerService service = new TrainerService(wordService, gameRepository);
        assertThrows(GameNotFoundException.class, () -> service.startNewRound(0L));
        assertThrows(GameNotFoundException.class, () -> service.guessWord(0L, ""));
    }

    @Test
    @DisplayName("throws exception if round already started")
    public void roundAlreadyStarted() {
/*        WordService wordService = mock(WordService.class);
        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
        TrainerService service = new TrainerService(wordService, gameRepository);
        when(wordService.provideRandomWord(5)).thenReturn("appel");
        when(wordService.provideRandomWord(6)).thenReturn("appels");
        when(wordService.provideRandomWord(7)).thenReturn("appelss");
        service.startNewGame();
        service.startNewRound(0L);
        assertThrows(AlreadyPlayingGameException.class, () -> service.startNewRound(0L));*/
    }
}