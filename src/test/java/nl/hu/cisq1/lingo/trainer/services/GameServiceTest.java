package nl.hu.cisq1.lingo.trainer.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.GameStatus;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import nl.hu.cisq1.lingo.trainer.domain.Round;
import nl.hu.cisq1.lingo.trainer.repositories.GameRepository;
import nl.hu.cisq1.lingo.trainer.repositories.ProgressRepository;
import nl.hu.cisq1.lingo.trainer.repositories.RoundRepository;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.data.SpringWordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {GameService.class, WordService.class})
@ExtendWith(SpringExtension.class)
class GameServiceTest {
    @MockBean
    private GameRepository gameRepository;

    @Autowired
    private GameService gameService;

    @MockBean
    private ProgressRepository progressRepository;

    @MockBean
    private RoundRepository roundRepository;

    @MockBean
    private WordService wordService;


    @Test
    void testNewGame() {
        Progress progress = new Progress();
        progress.setGameStatus(GameStatus.GAME_STARTED_WAITING_FOR_NEW_ROUND);
        progress.setRoundNumber(10);
        progress.setScore(3);
        when(this.progressRepository.save((Progress) any())).thenReturn(progress);

        Game game = new Game();
        game.setCurrentRound(new Round());
        when(this.gameRepository.save((Game) any())).thenReturn(game);
        assertSame(progress, this.gameService.newGame());
        verify(this.progressRepository).save((Progress) any());
        verify(this.gameRepository).save((Game) any());
    }

    @Test
    void testNewGameRound() {
        when(this.wordService.provideRandomWord((Integer) any())).thenReturn("Provide Random Word");
        when(this.roundRepository.save((Round) any())).thenReturn(new Round());

        Progress progress = new Progress();
        progress.setGameStatus(GameStatus.GAME_STARTED_WAITING_FOR_NEW_ROUND);
        progress.setRoundNumber(10);
        progress.setScore(3);

        Progress progress1 = new Progress();
        progress1.setGameStatus(GameStatus.GAME_STARTED_WAITING_FOR_NEW_ROUND);
        progress1.setRoundNumber(10);
        progress1.setScore(3);
        when(this.progressRepository.save((Progress) any())).thenReturn(progress1);
        when(this.progressRepository.findByGame((Game) any())).thenReturn(progress);

        Game game = new Game();
        game.setCurrentRound(new Round());
        Optional<Game> ofResult = Optional.<Game>of(game);

        Game game1 = new Game();
        game1.setCurrentRound(new Round());
        when(this.gameRepository.save((Game) any())).thenReturn(game1);
        when(this.gameRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(progress1, this.gameService.newGameRound(123L));
        verify(this.wordService).provideRandomWord((Integer) any());
        verify(this.roundRepository).save((Round) any());
        verify(this.progressRepository).findByGame((Game) any());
        verify(this.progressRepository).save((Progress) any());
        verify(this.gameRepository).findById((Long) any());
        verify(this.gameRepository).save((Game) any());
    }

    @Test
    void testNewGameRound2() {
        when(this.wordService.provideRandomWord((Integer) any())).thenReturn("Provide Random Word");
        when(this.roundRepository.save((Round) any())).thenReturn(new Round());

        Progress progress = new Progress();
        progress.setGameStatus(GameStatus.GAME_STARTED_WAITING_FOR_NEW_ROUND);
        progress.setRoundNumber(10);
        progress.setScore(3);

        Progress progress1 = new Progress();
        progress1.setGameStatus(GameStatus.GAME_STARTED_WAITING_FOR_NEW_ROUND);
        progress1.setRoundNumber(10);
        progress1.setScore(3);
        when(this.progressRepository.save((Progress) any())).thenReturn(progress1);
        when(this.progressRepository.findByGame((Game) any())).thenReturn(progress);

        Game game = new Game();
        game.setCurrentRound(null);
        Optional<Game> ofResult = Optional.<Game>of(game);

        Game game1 = new Game();
        game1.setCurrentRound(new Round());
        when(this.gameRepository.save((Game) any())).thenReturn(game1);
        when(this.gameRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(progress1, this.gameService.newGameRound(123L));
        verify(this.wordService).provideRandomWord((Integer) any());
        verify(this.roundRepository).save((Round) any());
        verify(this.progressRepository).findByGame((Game) any());
        verify(this.progressRepository).save((Progress) any());
        verify(this.gameRepository).findById((Long) any());
        verify(this.gameRepository).save((Game) any());
    }

    @Test
    void testUpdateGameProgress() {
        Progress progress = new Progress();
        progress.setGameStatus(GameStatus.GAME_STARTED_WAITING_FOR_NEW_ROUND);
        progress.setRoundNumber(10);
        progress.setScore(3);

        Progress progress1 = new Progress();
        progress1.setGameStatus(GameStatus.GAME_STARTED_WAITING_FOR_NEW_ROUND);
        progress1.setRoundNumber(10);
        progress1.setScore(3);
        when(this.progressRepository.save((Progress) any())).thenReturn(progress1);
        when(this.progressRepository.findByGame((Game) any())).thenReturn(progress);

        Game game = new Game();
        game.setCurrentRound(new Round());
        this.gameService.updateGameProgress(game, GameStatus.GAME_STARTED_WAITING_FOR_NEW_ROUND);
        verify(this.progressRepository).findByGame((Game) any());
        verify(this.progressRepository).save((Progress) any());
    }

    @Test
    void testGetGameProgress() {
        Progress progress = new Progress();
        progress.setGameStatus(GameStatus.GAME_STARTED_WAITING_FOR_NEW_ROUND);
        progress.setRoundNumber(10);
        progress.setScore(3);
        when(this.progressRepository.findByGame((Game) any())).thenReturn(progress);

        Game game = new Game();
        game.setCurrentRound(new Round());
        Optional<Game> ofResult = Optional.<Game>of(game);
        when(this.gameRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(progress, this.gameService.getGameProgress(123L));
        verify(this.progressRepository).findByGame((Game) any());
        verify(this.gameRepository).findById((Long) any());
    }

    @Test
    void testGameGuessWord() {
        when(this.roundRepository.save((Round) any())).thenReturn(new Round());

        Progress progress = new Progress();
        progress.setGameStatus(GameStatus.GAME_STARTED_WAITING_FOR_NEW_ROUND);
        progress.setRoundNumber(10);
        progress.setScore(3);

        Progress progress1 = new Progress();
        progress1.setGameStatus(GameStatus.GAME_STARTED_WAITING_FOR_NEW_ROUND);
        progress1.setRoundNumber(10);
        progress1.setScore(3);
        when(this.progressRepository.save((Progress) any())).thenReturn(progress1);
        when(this.progressRepository.findByGame((Game) any())).thenReturn(progress);
        Round round = mock(Round.class);
        Feedback feedback = new Feedback("Word To Guess", "Guess Word");

        when(round.GetGuessResult((String) any())).thenReturn(feedback);

        Game game = new Game();
        game.setCurrentRound(round);
        Optional<Game> ofResult = Optional.<Game>of(game);
        when(this.gameRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(feedback, this.gameService.gameGuessWord(123L, "Word Guess"));
        verify(this.roundRepository).save((Round) any());
        verify(this.progressRepository).findByGame((Game) any());
        verify(this.progressRepository).save((Progress) any());
        verify(this.gameRepository).findById((Long) any());
        verify(round).GetGuessResult((String) any());
    }
}

