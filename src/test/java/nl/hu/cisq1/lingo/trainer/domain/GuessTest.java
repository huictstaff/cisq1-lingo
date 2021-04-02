package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidCharacterException;
import org.checkerframework.checker.lock.qual.GuardedBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuessTest {

    @Test
    @DisplayName("Constructor with inavlid character")
    void invalidConstructor() {
        assertThrows(InvalidCharacterException.class, () -> new Guess("drop☺ul"));
    }

    @Test
    @DisplayName("Constructor with valid characters")
    void validConstructor() {
        assertDoesNotThrow(() -> new Guess("droplul"));
    }

    @Test
    @DisplayName("Get guess")
    void getGuess() {
        Guess guess = new Guess("droplui");
        assertEquals("droplui", guess.getGuess());
    }

    @Test
    @DisplayName("validate guess with non letter char and correct word")
    void composedOfLettersIsFalse() {
        Guess guess = new Guess("dropjes");
        assertFalse(guess.validateGuess("dropli♦", "droplul"));
    }

    @Test
    @DisplayName("Validate guess with all letter chars and correct word length")
    void composedOfLettersIsTrue() {
        Guess guess = new Guess("dropjes");
        assertTrue(guess.validateGuess("doprens", "droplul"));
    }

    @Test
    @DisplayName("Validate guess with wrong word length and correct guess")
    void validateGuessWrongWordLength() {
        Guess guess = new Guess("guess");
        assertFalse(guess.validateGuess("guess", "gues"));
    }

    @Test
    @DisplayName("Validate guess with correct word length and letters only")
    void validateGuessWithCorrectWordLength() {
        Guess guess = new Guess("guess");
        assertFalse(guess.validateGuess("guess", "gues"));
    }

    @Test
    @DisplayName("Validate guess with non letter char in word and correct guess")
    void validateGuessWithNonLetterCharWord() {
        Guess guess = new Guess("guess");
        assertFalse(guess.validateGuess("guess", "wo®d"));
    }
}