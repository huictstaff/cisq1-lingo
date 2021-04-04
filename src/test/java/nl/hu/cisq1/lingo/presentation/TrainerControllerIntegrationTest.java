package nl.hu.cisq1.lingo.presentation;

import nl.hu.cisq1.lingo.CiTestConfiguration;
import nl.hu.cisq1.lingo.application.TrainerService;
import nl.hu.cisq1.lingo.data.SpringGameRepository;
import nl.hu.cisq1.lingo.domain.Game;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Handige MockMvcResultMatchers:
//.status();
//.content();
//.header();
//.jsonPath();

@SpringBootTest
@AutoConfigureMockMvc
@Import(CiTestConfiguration.class)
public class TrainerControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    private Game game;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private SpringGameRepository gameRepository;

    @BeforeEach
    void beforeEachTest() {
        this.game = trainerService.startNewGame();
    }

    @AfterEach
    void afterEachTest(){
        if(gameRepository.findById(game.getId()).isPresent()) gameRepository.delete(game);
    }

    @Test
    @DisplayName("Requesting game with getGame returns ProcessDTO")
    void requestGame() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders
                .get("/trainer/game/" + this.game.getId());
        mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Not found game is catched.")
    void noGameFoundException() throws Exception{
        if(gameRepository.findById(1L).isPresent()) gameRepository.deleteById(1L);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/trainer/game/1");
        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Creating game returns correct DTO")
    void gameReturnsCorrectDTO() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.post("/trainer/game/start");
        mockMvc.perform(request).andExpect(status().isOk()); //.andExpect(jsonPath("$.gameId" ));
    }

}
