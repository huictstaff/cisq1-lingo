package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.Mark;
import nl.hu.cisq1.lingo.trainer.presentation.dto.BeginWord;
import nl.hu.cisq1.lingo.trainer.presentation.dto.GameStatus;
import nl.hu.cisq1.lingo.trainer.presentation.dto.Guess;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainerServiceTest {

    @ParameterizedTest
    @DisplayName("requests the status of a game through it's repository")
    @MethodSource("randomGameExamples")
    void providesCorrectGameStatus(long id, Game game) throws Exception {
        SpringGameRepository mockRepository = mock(SpringGameRepository.class);
        when(mockRepository.findById(id))
                .thenReturn(Optional.of(game));

        TrainerService service = new TrainerService(mockRepository);
        GameStatus result = service.getStatus(id);

        assertEquals(id, result.getId());
        assertEquals(game.getCurrentRound().getLastHint(), result.getCurrentHint());
    }

    @Test
    @DisplayName("returns gamestatus for new game without feedback")
    void startsNewGame() {
        SpringGameRepository mockRepository = mock(SpringGameRepository.class);
        when(mockRepository.save(Mockito.any(Game.class)))
                .thenAnswer(i -> {
                    Game game = (Game) i.getArguments()[0];
                    game.setId(1L);
                    return game;
                });

        TrainerService service = new TrainerService(mockRepository);
        BeginWord beginWord = new BeginWord();
        beginWord.word = "appel";


        GameStatus status = service.provideNewGame(beginWord);

        assertNotNull(status.getCurrentHint());
        assertNotNull(status.getId());
        assertNull(status.getLastFeedback());
    }

    @ParameterizedTest
    @DisplayName("guesses and gets feedback")
    @MethodSource("randomGameExamples")
    void guessAnswer(long id, Game game) throws Exception {
        SpringGameRepository mockRepository = mock(SpringGameRepository.class);
        when(mockRepository.findById(id))
                .thenReturn(Optional.of(game));

        TrainerService service = new TrainerService(mockRepository);
        Guess guess = new Guess();
        guess.guess = game.getCurrentRound().getWordToGuess();

        GameStatus status = service.guess(id, guess);

        assertFalse(status.isLost());
        assertNotNull(status.getId());
        assertNotNull(status.getCurrentHint());
        assertEquals(Mark.CORRECT, status.getLastFeedback().totalMark());
        assertEquals(game.getCurrentRound().getWordToGuess(), status.getCurrentHint().getHintString());

    }

    static Stream<Arguments> randomGameExamples() {
        return Stream.of(
                Arguments.of(1, new Game(1L, "pizza")),
                Arguments.of(2, new Game(2L, "castle")),
                Arguments.of(3, new Game(3L, "knights"))
        );
    }
}