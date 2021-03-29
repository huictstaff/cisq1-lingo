package nl.hu.cisq1.lingo.trainer.domain.exception;

import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Hint;
import nl.hu.cisq1.lingo.trainer.domain.Mark;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InvalidFeedbackExceptionTest {
    @Test
    @DisplayName("feedback throws an error when checking if the word is guessed")
    void feedbackThrowsError() {
        Feedback feedback = new Feedback(null, null);
        assertThrows(InvalidFeedbackException.class, feedback::wordIsGuessed);
    }

    @Test
    @DisplayName("getting a hint with invalid guess should throw an error")
    void gettingHint() {
        Feedback feedback = new Feedback("word", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        Hint previousHint = Hint.initialHint(Feedback.initialFeedback("word").getMarks(), 'w', 4);
        assertThrows(InvalidFeedbackException.class, () -> feedback.giveHint(previousHint));
    }
}