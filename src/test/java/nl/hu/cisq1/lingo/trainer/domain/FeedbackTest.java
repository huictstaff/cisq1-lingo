package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import nl.hu.cisq1.lingo.trainer.domain.Mark;
import static org.mockito.Mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    @Test
    @DisplayName("Word is guessed if all letters are correct")
    void wordIsGuessed(){
        var result = List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT);
        Feedback f = new Feedback("woord", result);

        assertTrue(f.isWordGuessed());
    }
    @Test
    @DisplayName("Some letters are incorrect")
    void wordIsNotGuessed(){
        var result = List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.INVALID, Mark.CORRECT);
        Feedback f = new Feedback("woord", result);

        assertFalse(f.isWordGuessed());
    }
}