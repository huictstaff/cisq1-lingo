package nl.hu.cisq1.lingo.trainer.presentation.DTO;

import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Mark;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackDTOTest {

    @Test
    void setMarks() {
        FeedbackDTO feedbackDTO= new FeedbackDTO(new Feedback());
        feedbackDTO.setMarks(List.of(Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT));
        assertEquals(List.of(Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT),feedbackDTO.getMarks());
    }

    @Test
    void setAttempt() {
        FeedbackDTO feedbackDTO = new FeedbackDTO(new Feedback());
        feedbackDTO.setAttempt("woord");
        assertEquals("woord", feedbackDTO.getAttempt());
    }

    @Test
    void testToString() {
        FeedbackDTO feedbackDTO = new FeedbackDTO(new Feedback());
        assertNotNull(feedbackDTO.toString());
    }
}