//package nl.hu.cisq1.lingo.lingoTrainer.controllers;
//
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.util.ArrayList;
//
//import nl.hu.cisq1.lingo.lingoTrainer.domain.Feedback;
//import nl.hu.cisq1.lingo.lingoTrainer.domain.Mark;
//import nl.hu.cisq1.lingo.lingoTrainer.services.FeedbackService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//@ContextConfiguration(classes = {FeedbackController.class})
//@ExtendWith(SpringExtension.class)
//class FeedbackControllerTest {
//    @Autowired
//    private FeedbackController feedbackController;
//
//    @MockBean
//    private FeedbackService feedbackService;
//
//    @Test
//    void testSave() throws Exception {
//        Feedback feedback = new Feedback();
//        feedback.setMarks(new ArrayList<Mark>());
//        feedback.calculateMarks("42", "Attempt");
//        when(this.feedbackService.save((Feedback) any())).thenReturn(feedback);
//
//        Feedback feedback1 = new Feedback();
//        feedback1.setMarks(new ArrayList<Mark>());
//        feedback1.calculateMarks("42", "Attempt");
//        String content = (new ObjectMapper()).writeValueAsString(feedback1);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/feedback")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        MockMvcBuilders.standaloneSetup(this.feedbackController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string("{\"marks\":[\"ABSENT\",\"ABSENT\"],\"wordGuessed\":false}"));
//    }
//
//    @Test
//    void testDeleteAll() throws Exception {
//        doNothing().when(this.feedbackService).deleteAll();
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/feedback");
//        MockMvcBuilders.standaloneSetup(this.feedbackController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    void testDeleteAll2() throws Exception {
//        doNothing().when(this.feedbackService).deleteAll();
//        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/feedback");
//        deleteResult.contentType("Not all who wander are lost");
//        MockMvcBuilders.standaloneSetup(this.feedbackController)
//                .build()
//                .perform(deleteResult)
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    void testCount() throws Exception {
//        when(this.feedbackService.count()).thenReturn(3L);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/feedback/count");
//        MockMvcBuilders.standaloneSetup(this.feedbackController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string("3"));
//    }
//
//    @Test
//    void testDeleteById() throws Exception {
//        doNothing().when(this.feedbackService).delete((Long) any());
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/feedback/{id}", 123L);
//        MockMvcBuilders.standaloneSetup(this.feedbackController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    void testGetAll() throws Exception {
//        when(this.feedbackService.findAll()).thenReturn(new ArrayList<Feedback>());
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/feedback");
//        MockMvcBuilders.standaloneSetup(this.feedbackController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string("[]"));
//    }
//
//    @Test
//    void testGetAll2() throws Exception {
//        when(this.feedbackService.findAll()).thenReturn(new ArrayList<Feedback>());
//        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/feedback");
//        getResult.contentType("Not all who wander are lost");
//        MockMvcBuilders.standaloneSetup(this.feedbackController)
//                .build()
//                .perform(getResult)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string("[]"));
//    }
//
//    @Test
//    void testGetById() throws Exception {
//        Feedback feedback = new Feedback();
//        feedback.setMarks(new ArrayList<Mark>());
//        feedback.calculateMarks("42", "Attempt");
//        when(this.feedbackService.find((Long) any())).thenReturn(feedback);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/feedback/{id}", 123L);
//        MockMvcBuilders.standaloneSetup(this.feedbackController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string("{\"marks\":[\"ABSENT\",\"ABSENT\"],\"wordGuessed\":false}"));
//    }
//}
//
