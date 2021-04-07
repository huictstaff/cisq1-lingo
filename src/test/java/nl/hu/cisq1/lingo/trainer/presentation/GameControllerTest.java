package nl.hu.cisq1.lingo.trainer.presentation;

import com.jayway.jsonpath.JsonPath;
import nl.hu.cisq1.lingo.CiTestConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Import(CiTestConfiguration.class)
@AutoConfigureMockMvc
@ActiveProfiles("ci")
class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private int id;

    @BeforeEach
    void setUp() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/trainer/games");
        MvcResult result = mockMvc.perform(request).andDo(print()).andReturn();
        this.id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
    }

    @Test
    void createGame() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/trainer/games");

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.rounds", hasSize(1)))
                .andExpect(jsonPath("$.rounds[0].hints", hasSize(1)))
                .andExpect(jsonPath("$.rounds[0].feedback", hasSize(1)))
                .andExpect(jsonPath("$.rounds[0].state", is("IN_PROGRESS"))).andReturn();


    }

    @Test
    void guess() throws Exception {
        String guess = "{\"guess\":\"pizaz\"}";

        RequestBuilder request = MockMvcRequestBuilders
                .post("/trainer/games/"+ this.id +"/guess")
                .content(guess)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(this.id)))
                .andExpect(jsonPath("$.rounds[-1].hints[1].guess", is("pizaz")))
                .andExpect(jsonPath("$.rounds[0].state", is("IN_PROGRESS")));
    }

    @Test
    void getById() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/trainer/games/" + this.id);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(this.id)));
    }

    @Test
    void newRound() throws Exception {
        String guess = "{\"guess\":\"pizza\"}";
        RequestBuilder winningGuess = MockMvcRequestBuilders
                .post("/trainer/games/"+ this.id + "/guess")
                .content(guess)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(winningGuess)
                .andExpect(status().isOk());

        RequestBuilder newRound = MockMvcRequestBuilders
                .post("/trainer/games/" + this.id);

        mockMvc.perform(newRound)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.rounds", hasSize(2)));
    }
}