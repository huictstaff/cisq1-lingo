package nl.hu.cisq1.lingo.trainer.domain.exception;

import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Hint;
import nl.hu.cisq1.lingo.trainer.domain.Mark;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InvalidGuessExceptionTest {
    @Test
    @DisplayName("feedback throws an error when checking if the word is guessed")
    void feedbackThrowsError() {
        Feedback feedback = new Feedback(null, null);
        assertThrows(InvalidGuessException.class, feedback::wordIsGuessed);
    }

    @Test
    @DisplayName("getting a hint with invalid guess in the feedback class should throw an error")
    void gettingHintFromFeedback() {
        Feedback feedback = new Feedback("word", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        Hint previousHint = Hint.initialHint(Feedback.initialFeedback("word").getMarks(), 'w', 4);
        assertThrows(InvalidGuessException.class, () -> feedback.giveHint(previousHint));
    }

    @Test
    @DisplayName("getting a hint with invalid guess should throw an error")
    void gettingHintFromHint() {
        Hint hint = Hint.initialHint(Feedback.initialFeedback("word").getMarks(), 'w', 4);
        assertThrows(InvalidGuessException.class, hint::getHint);
    }
}