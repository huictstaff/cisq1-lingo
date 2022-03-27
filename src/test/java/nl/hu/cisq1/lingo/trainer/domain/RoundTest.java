package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exeption.GameException;
import nl.hu.cisq1.lingo.trainer.domain.exeption.InvalidAttemptException;
import nl.hu.cisq1.lingo.trainer.domain.exeption.TooManyAttemptsException;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.ABSENT;
import static nl.hu.cisq1.lingo.trainer.domain.Mark.CORRECT;
import static org.junit.jupiter.api.Assertions.*;

class RoundTest {

    @Test
    @DisplayName("Test if the word was successfully guessed this attempt")
    void isWordGuessed() {
        Round round = new Round(new Word("lingo"));
        assertTrue(round.isWordGuessed("lingo"));
    }

    @Test
    @DisplayName("Test if the word was unsuccessfully guessed this attempt")
    void isWordNotGuessed() {
        Round round = new Round(new Word("lingo"));
        assertFalse(round.isWordGuessed("lango"));
    }

    @Test
    @DisplayName("Test if invaled length gets catched by attempt")
    void isWordOfInvalidLength() {
        Round round = new Round(new Word("lingo"));
        assertFalse(round.attemptValid("langer"));
    }

    @ParameterizedTest
    @MethodSource("wordLenghtInputStream")
    @DisplayName("Test if the length of the word is valid")
    void isWordOfValidLength(String word, String attempt, Boolean expectedResult) {
        Round round = new Round(new Word(word));
        assertEquals(expectedResult, round.isWordLengthValid(attempt));
    }

    static Stream<Arguments> wordLenghtInputStream() {
        return Stream.of(
                /** If 5 letter word needs to be guessed */
            Arguments.of("BRAAM", "BIER", false),
            Arguments.of("BRAAM", "BOTER", true),
            Arguments.of("BRAAM", "BRODEN", false),

                /** If 6 letter word needs to be guessed */
            Arguments.of("BAKKER", "BOTER", false),
            Arguments.of("BAKKER", "BRODEN", true),
            Arguments.of("BAKKER", "BANANEN", false),

                /** If 7 letter word needs to be guessed */
            Arguments.of("BAKKERS", "BRODEN", false),
            Arguments.of("BAKKERS", "BANANEN", true),
            Arguments.of("BAKKERS", "HAMBURGER", false),

                /** If word length < 5 or > 7 */
            Arguments.of("BAKKER", "BIER", false),
            Arguments.of("BAKKER", "HAMBURGER", false)

        );
    }

    @Test
    @DisplayName("Is there feedback?")
    void returnValidFeedback() {
        Round round = new Round(new Word("woord"));
        round.attempt("brood");
        assertEquals(1, round.getFeedback().size());
    }

//
//    /** TODO stream arguments to test the creation of hint with no guesses, 1 guess, multiple guesses */
//    void createHint() {
//
//    }
//
    /** TODO test when word is guessed correctly */
    @Test
    @DisplayName("test when the correct guess is made if the marks are correct")
    void correctGuess() {
        Round round = new Round(new Word("brood"));
        round.attempt("braak");
        round.attempt("brood");
        assertEquals(true, round.hasWordBeenGuessed);
    }

    /** TODO make attempt function catch and send error message on invalid attempt with if (!attemptValid... */
    @Test
    @DisplayName("test if an error is thrown if an invalid attempt is made ")
    void throwInvalidAttemptException() {
        Round round = new Round(new Word("brood"));

        assertThrows(InvalidAttemptException.class, () -> round.attempt("bramen"));
    }
//
//    /** TODO test after word is guessed correctly and if round is over */
    @Test
    @DisplayName("test if the round is over after the word is guessed correctly")
    void throwGameExceptionAtGuessingCorrectly() {
        Round round = new Round(new Word("brood"));
        round.attempt("brood");
        assertEquals(true, round.hasWordBeenGuessed);
    }
//
//
//    /** TODO test if round is over after five incorrect guesses */
    @Test
    @DisplayName("Check if 5 attempts do result in a feedback containing all attempts and feedback")
    void feedbackContainsAllAndCorrectData() {
        Round round = new Round(new Word("lingo"));
        round.attempt("lango");
        round.attempt("lengo");
        round.attempt("lungo");
        round.attempt("longo");
        round.attempt("lyngo");
        List<Feedback> feedback = round.getFeedback();
        Feedback feedbackElement = feedback.get(4);
        /** does contain 5 elements */
        assertTrue(round.getFeedback().size() == 5);
        /** last element contains correct data */
        assertTrue(feedbackElement.getAttempt().equals("lyngo"));
        assertTrue(feedbackElement.getMarks().equals(List.of(CORRECT, ABSENT, CORRECT, CORRECT, CORRECT)));

    }

    @Test
    @DisplayName("Check if 5 attempts do result in a feedback containing all attempts and feedback")
    void roundOverBecauseLimitOfAttempts() {
        Round round = new Round(new Word("lingo"));
        round.attempt("lango");
        round.attempt("lengo");
        round.attempt("lungo");
        round.attempt("longo");
        round.attempt("lyngo");

        assertThrows(TooManyAttemptsException.class, () -> round.attempt("llngo"));
    }



//
//    /** TODO test if word is already attempted */

}