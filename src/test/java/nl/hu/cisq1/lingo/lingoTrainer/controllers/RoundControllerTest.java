package nl.hu.cisq1.lingo.lingoTrainer.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import nl.hu.cisq1.lingo.lingoTrainer.domain.Round;
import nl.hu.cisq1.lingo.lingoTrainer.services.RoundService;
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

@ContextConfiguration(classes = {RoundController.class})
@ExtendWith(SpringExtension.class)
class RoundControllerTest {
    @Autowired
    private RoundController roundController;

    @MockBean
    private RoundService roundService;

    @Test
    void testDeleteAll() throws Exception {
        doNothing().when(this.roundService).deleteAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/round");
        MockMvcBuilders.standaloneSetup(this.roundController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteAll2() throws Exception {
        doNothing().when(this.roundService).deleteAll();
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/round");
        deleteResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.roundController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testCount() throws Exception {
        when(this.roundService.count()).thenReturn(3L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/round/count");
        MockMvcBuilders.standaloneSetup(this.roundController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("3"));
    }

    @Test
    void testDeleteById() throws Exception {
        doNothing().when(this.roundService).delete((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/round/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.roundController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetAll() throws Exception {
        when(this.roundService.findAll()).thenReturn(new ArrayList<Round>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/round");
        MockMvcBuilders.standaloneSetup(this.roundController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAll2() throws Exception {
        when(this.roundService.findAll()).thenReturn(new ArrayList<Round>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/round");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.roundController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetById() throws Exception {
        when(this.roundService.find((Long) any())).thenReturn(new Round("Word To Guess"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/round/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.roundController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"previousHint\":\"W,.,.,.,.,.,.,.,.,.,.,.,.\",\"wordToGuess\":\"Word To Guess\",\"timesGuessed\":0}"));
    }

    @Test
    void testSave() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/round")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new Round("Word To Guess")));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.roundController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

