package nl.hu.cisq1.lingo.words.presentation;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import nl.hu.cisq1.lingo.lingoTrainer.domain.exception.WordLengthNotSupportedException;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.data.SpringWordRepository;
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

@ContextConfiguration(classes = {RandomWordController.class})
@ExtendWith(SpringExtension.class)
class RandomWordControllerTest {
    @Autowired
    private RandomWordController randomWordController;

    @MockBean
    private WordService wordService;

    @Test
    void testConstructor() {
        // TODO: This test is incomplete.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     RandomWordController.service

        new RandomWordController(new WordService(mock(SpringWordRepository.class)));
    }

    @Test
    void testConstructor2() {
        // TODO: This test is incomplete.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     RandomWordController.service

        new RandomWordController(new WordService(mock(SpringWordRepository.class)));
    }

    @Test
    void testGetRandomWord() throws Exception {
        when(this.wordService.provideRandomWord((Integer) any())).thenReturn("Provide Random Word");
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/words/random");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("length", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.randomWordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Provide Random Word"));
    }

    @Test
    void testGetRandomWord2() throws Exception {
        when(this.wordService.provideRandomWord((Integer) any())).thenThrow(new WordLengthNotSupportedException(3));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/words/random");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("length", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.randomWordController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testGetRandomWord3() throws Exception {
        when(this.wordService.provideRandomWord((Integer) any())).thenReturn("Provide Random Word");
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/words/random");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("length", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.randomWordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Provide Random Word"));
    }

    @Test
    void testGetRandomWord4() throws Exception {
        when(this.wordService.provideRandomWord((Integer) any())).thenThrow(new WordLengthNotSupportedException(3));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/words/random");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("length", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.randomWordController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

