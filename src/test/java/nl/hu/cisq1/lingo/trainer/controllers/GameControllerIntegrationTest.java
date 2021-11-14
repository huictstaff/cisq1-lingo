package nl.hu.cisq1.lingo.trainer.controllers;

import nl.hu.cisq1.lingo.CiTestConfiguration;
import nl.hu.cisq1.lingo.trainer.services.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;


@SpringBootTest
@Import(CiTestConfiguration.class)
@AutoConfigureMockMvc
public class GameControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testNewGame() throws Exception
    {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/game/newGame").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(jsonPath("$.score", is(0)))
                .andExpect(jsonPath("$.gameStatus", is("GAME_STARTED_WAITING_FOR_NEW_ROUND")));

    }

    @Test
    void testGetGameProgress() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/game/getGameProgress").param("gameId", "1");

        mockMvc.perform(request)
                .andExpect(jsonPath("$.gameStatus", is("GAME_STARTED_WAITING_FOR_NEW_ROUND")))
                .andExpect(jsonPath("$.gameId", is(1)));

    }

    @Test
    void testNewGameRound() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/game/newGameRound").param("gameId", "1");

        mockMvc.perform(request)
                .andExpect(jsonPath("$.gameStatus", is("ROUND_STARTED_WAITING_FOR_NEW_GUESS")))
                .andExpect(jsonPath("$.gameId", is(1)));

    }

    @Test
    void testGameGuessWord() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/game/gameGuessWord")
                .param("wordGuess", "appel")
                .param("gameId", "1");


        mockMvc.perform(request)
                .andExpect(jsonPath("$.guessWord", is("appel")));
    }
}