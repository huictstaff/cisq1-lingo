package nl.hu.cisq1.lingo.trainer.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hu.cisq1.lingo.CiTestConfiguration;
import nl.hu.cisq1.lingo.trainer.data.GameRepository;
import nl.hu.cisq1.lingo.trainer.domain.LingoGame;
import nl.hu.cisq1.lingo.trainer.presentation.dto.GuessDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Import(CiTestConfiguration.class)
@AutoConfigureMockMvc
public class GameControllerIntegrationTest {
    private LingoGame game;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GameRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void initalize() {
        this.game = new LingoGame();
        this.game.newRound("appel");
        this.repository.save(game);
    }

    @Test
    @DisplayName("Start game succesfully")
    void startGameSuccesfully() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/lingo/startgame");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Make a succesfull first guess")
    void makeSuccesfullGuess() throws Exception {
        GuessDTO dto = new GuessDTO();
        dto.guess = "appel";

        RequestBuilder request = MockMvcRequestBuilders
                .post("/lingo/guess?gameId=" + this.game.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(dto));

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Make guess where word does not exist")
    void makeGuessWordNotExist() throws Exception {
        GuessDTO dto = new GuessDTO();
        dto.guess = "bestaatniet";

        RequestBuilder request = MockMvcRequestBuilders
                .post("/lingo/guess?gameId=" + this.game.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(dto));

        this.mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Make guess where game does not exist")
    void makeGuessGameNoExist() throws Exception {
        GuessDTO dto = new GuessDTO();
        dto.guess = "appel";

        RequestBuilder request = MockMvcRequestBuilders
                .post("/lingo/guess?gameId=" + 99999999)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(dto));

        this.mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Make guess but word is too long")
    void makeGuessGameWordTooLong() throws Exception {
        GuessDTO dto = new GuessDTO();
        dto.guess = "keuter";

        RequestBuilder request = MockMvcRequestBuilders
                .post("/lingo/guess?gameId=" + game.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(dto));

        this.mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }
}
