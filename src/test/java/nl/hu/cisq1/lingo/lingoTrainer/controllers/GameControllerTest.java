package nl.hu.cisq1.lingo.lingoTrainer.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import nl.hu.cisq1.lingo.lingoTrainer.domain.Game;
import nl.hu.cisq1.lingo.lingoTrainer.services.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
    void testDeleteAll() throws Exception {
        doNothing().when(this.gameService).deleteAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/game");
        MockMvcBuilders.standaloneSetup(this.gameController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteAll2() throws Exception {
        doNothing().when(this.gameService).deleteAll();
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/game");
        deleteResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.gameController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testCount() throws Exception {
        when(this.gameService.count()).thenReturn(3L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/game/count");
        MockMvcBuilders.standaloneSetup(this.gameController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("3"));
    }

    @Test
    void testDeleteById() throws Exception {
        doNothing().when(this.gameService).delete((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/game/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.gameController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetAll() throws Exception {
        when(this.gameService.findAll()).thenReturn(new ArrayList<Game>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/game");
        MockMvcBuilders.standaloneSetup(this.gameController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAll2() throws Exception {
        when(this.gameService.findAll()).thenReturn(new ArrayList<Game>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/game");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.gameController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetById() throws Exception {
        when(this.gameService.find((Long) any())).thenReturn(new Game("Word To Guess"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/game/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.gameController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"rounds\":[],\"currentRound\":{\"previousHint\":\"W,.,.,.,.,.,.,.,.,.,.,.,.\",\"wordToGuess\":\"Word To"
                                        + " Guess\",\"timesGuessed\":0}}"));
    }

    @Test
    void testSave() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/game")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new Game("Word To Guess")));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gameController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

