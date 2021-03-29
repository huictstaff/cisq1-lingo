package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HintTest {
    @Test
    @DisplayName("Empty or invalid hints should not generate")
    void emptyHint() {
        Hint hint = new Hint();
        int size = hint.getHint().size();
        assertEquals(0, size);
    }

    @Test
    @DisplayName("hint constructor succesvol hint moeten genereren")
    void hintConstructor() {
        String word = "woord";
        String guess = "worod";
        Feedback feedback = Feedback.initialFeedback(word);
        Hint initialHint = Hint.initialHint(feedback.getMarks(), 'w', 5);
        Validator validator = new Validator(guess, word);
        Feedback feedback1 = new Feedback(guess, validator.validate());

        Hint hint = new Hint(initialHint, feedback1);

        assertEquals(guess.length(), hint.getHint().size());
    }

    @Test
    @DisplayName("static function should generate a hint with one character")
    void initialHint() {
        Feedback feedback = Feedback.initialFeedback("woord");
        Hint hint = Hint.initialHint(feedback.getMarks(), 'w', 5);
        List<Character> hints = hint.getHint();
        assertEquals(hints, List.of('w', '.', '.', '.', '.'));
    }
}