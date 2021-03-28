package nl.hu.cisq1.lingo.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
public class TrainerControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Not found game is catched.")
    void noGameFoundException() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders
                .get("/game/");
        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }


}
