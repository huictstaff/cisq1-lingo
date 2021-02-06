package nl.hu.cisq1.lingo.trainer.domain.exception;

import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Mark;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class InvalidFeedBackExceptionTest {

    @Test
    void InvalidFeedback(){
        assertThrows(
                InvalidFeedBackException.class,
                () -> new Feedback("woord", List.of(Mark.CORRECT)));
    }
}