package nl.hu.cisq1.lingo.trainer.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import nl.hu.cisq1.lingo.lingoTrainer.domain.exception.WordLengthNotSupportedException;
import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.GameStatus;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import nl.hu.cisq1.lingo.trainer.domain.exception.GameDoesNotExistException;
import nl.hu.cisq1.lingo.trainer.services.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {GameController.class})
@ExtendWith(SpringExtension.class)
class GameControllerTest {
    @Autowired
    private GameController gameController;

    @MockBean
    private GameService gameService;

    @Test
    void testNewGame() throws Exception {
        Progress progress = new Progress();
        progress.setGameStatus(GameStatus.GAME_STARTED_WAITING_FOR_NEW_ROUND);
        progress.setRoundNumber(10);
        progress.setScore(3);
        when(this.gameService.newGame()).thenReturn(progress);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/game/newGame");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gameController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"score\":3,\"gameStatus\":\"GAME_STARTED_WAITING_FOR_NEW_ROUND\"}"));
    }

    @Test
    void testNewGame2() throws Exception {
        when(this.gameService.newGame()).thenThrow(new WordLengthNotSupportedException(3));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/game/newGame");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gameController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testNewGame3() throws Exception {
        Progress progress = new Progress(new Game());
        progress.setGameStatus(GameStatus.GAME_STARTED_WAITING_FOR_NEW_ROUND);
        progress.setRoundNumber(10);
        progress.setScore(3);
        when(this.gameService.newGame()).thenReturn(progress);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/game/newGame");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gameController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"score\":3,\"gameStatus\":\"GAME_STARTED_WAITING_FOR_NEW_ROUND\"}"));
    }

    @Test
    void testGetGameProgress() throws Exception {
        Progress progress = new Progress();
        progress.setGameStatus(GameStatus.GAME_STARTED_WAITING_FOR_NEW_ROUND);
        progress.setRoundNumber(10);
        progress.setScore(3);
        when(this.gameService.getGameProgress(anyLong())).thenReturn(progress);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/game/getGameProgress");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("gameId", String.valueOf(1L));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gameController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"score\":3,\"gameStatus\":\"GAME_STARTED_WAITING_FOR_NEW_ROUND\"}"));
    }

    @Test
    void testGetGameProgress2() throws Exception {
        when(this.gameService.getGameProgress(anyLong())).thenThrow(new GameDoesNotExistException(123L));
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/game/getGameProgress");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("gameId", String.valueOf(1L));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gameController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testNewGameRound() throws Exception {
        Progress progress = new Progress();
        progress.setGameStatus(GameStatus.GAME_STARTED_WAITING_FOR_NEW_ROUND);
        progress.setRoundNumber(10);
        progress.setScore(3);
        when(this.gameService.newGameRound(anyLong())).thenReturn(progress);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/game/newGameRound");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("gameId", String.valueOf(1L));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gameController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"score\":3,\"gameStatus\":\"GAME_STARTED_WAITING_FOR_NEW_ROUND\"}"));
    }

    @Test
    void testNewGameRound2() throws Exception {
        when(this.gameService.newGameRound(anyLong())).thenThrow(new WordLengthNotSupportedException(3));
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/game/newGameRound");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("gameId", String.valueOf(1L));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gameController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testGameGuessWord() throws Exception {
        when(this.gameService.gameGuessWord(anyLong(), (String) any()))
                .thenReturn(new Feedback("Word To Guess", "Guess Word"));
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/game/gameGuessWord");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("gameId", String.valueOf(1L))
                .param("wordGuess", "foo");
        MockMvcBuilders.standaloneSetup(this.gameController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"Id\":null,\"wordToGuess\":\"Word To Guess\",\"guessWord\":\"Guess Word\",\"marks\":[]}"));
    }

    @Test
    void testGameGuessWord2() throws Exception {
        when(this.gameService.gameGuessWord(anyLong(), (String) any())).thenThrow(new WordLengthNotSupportedException(3));
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/game/gameGuessWord");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("gameId", String.valueOf(1L))
                .param("wordGuess", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gameController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

