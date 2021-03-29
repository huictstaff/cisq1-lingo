package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorTest {
    @Test
    @DisplayName("list should be full of correct if the guess is correct")
    void validateCorrectLetters() {
        Validator validator = new Validator("woord", "woord");
        assertTrue(validator.validate().stream().allMatch(mark -> mark.equals(Mark.CORRECT)));
    }

    @Test
    @DisplayName("incorrect letters should be marked absent")
    void validateWrongLetters() {
        Validator validator = new Validator("woorr", "woord");
        assertEquals(validator.validate(), List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.ABSENT));
    }

    @Test
    @DisplayName("letters that are in the wrong place should be marked present")
    void validatePresentLetters() {
        Validator validator = new Validator("worod", "woord");
        assertEquals(validator.validate(), List.of(Mark.CORRECT, Mark.CORRECT, Mark.PRESENT, Mark.PRESENT, Mark.CORRECT));
    }

    @Test
    @DisplayName("there shouldn't be more letters marked present than are actually in the word")
    void validatePresentLetterAmount() {
        Validator validator = new Validator("wrood", "worrd");
        assertEquals(validator.validate(), List.of(Mark.CORRECT, Mark.PRESENT, Mark.PRESENT, Mark.ABSENT, Mark.CORRECT));
    }

    @Test
    @DisplayName("marks list should be filled with invalid if it is not the same length as the actual word")
    void validateValidGuesses() {
        Validator validator = new Validator("wooord", "woord");
        assertTrue(validator.validate().stream().allMatch(mark -> mark.equals(Mark.INVALID)));
    }

    @Test
    @DisplayName("invalid marks list should have same size as guess")
    void validateMarksListSize() {
        Validator validator = new Validator("wooord", "woord");
        assertEquals(6, validator.validate().size());
    }
}