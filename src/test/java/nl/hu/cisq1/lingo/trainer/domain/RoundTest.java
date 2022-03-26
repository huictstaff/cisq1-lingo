package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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


//    /** TODO test playing round an expected feedback */
//    void roundAttemptFeedback() {
//
//    }
//
//    /** TODO stream arguments to test the creation of hint with no guesses, 1 guess, multiple guesses */
//    void createHint() {
//
//    }
//
//    /** TODO test when word is guessed correctly */
//    void correctGuess() {
//
//    }
//
//    /** TODO test if word is guessed correctly and round is over */
//
//
//    /** TODO test if round is over after five incorrect guesses */
//    @Test
//    @DisplayName("")
//    void roundOverBecauseLimitOfAttempts() {
//        Round round = new Round(new Word("lingo"));
//        round.attempt("lango");
//        round.attempt("lengo");
//        round.attempt("lungo");
//        round.attempt("longo");
//        round.attempt("lyngo");
//        assertTrue(round.isRoundOver());
//
//    }
//
//    /** TODO test if word is already attempted */

}