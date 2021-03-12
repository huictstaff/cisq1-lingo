package nl.hu.cisq1.lingo.trainer.domain.exception;

import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Mark;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InvalidFeedbackExceptionTest {
    @Test
    @DisplayName("feedback throws error if guess and marks are different lengths")
    void feedbackThrowsError() {
        List<Mark> marks = new ArrayList<>();
        marks.add(Mark.CORRECT);
        assertThrows(InvalidFeedbackException.class, () -> new Feedback("woord", marks));
    }
}