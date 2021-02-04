package nl.hu.cisq1.lingo.trainer.domain.exception;

import static org.junit.jupiter.api.Assertions.*;

import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Mark;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedBackException;
import org.junit.jupiter.api.Test;

import java.util.List;

class InvalidFeedBackExceptionTest {

    @Test
    void InvalidFeedback(){
        assertThrows(
                InvalidFeedBackException.class,
                () -> new Feedback("woord", List.of(Mark.CORRECT)));
    }
}