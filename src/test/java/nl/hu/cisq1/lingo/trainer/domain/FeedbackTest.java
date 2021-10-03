package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Feedback;
import nl.hu.cisq1.lingo.words.domain.Game;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    @Test
    @DisplayName("Word is guessed if all letters are correct")
    void wordIsGuessed()
    {
        // Given - Pre-condition
        var feedback = new Feedback();

        // When - Action

        // Then - Post-condition
        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Word is not guessed if one of the letters in the word are wrong.")
    void wordIsNotGuessed()
    {
        // Given - Pre-condition
        var feedback = new Feedback();
        // When - Action
        feedback.giveHint(".,.,.,.,.,.", "winkel", "winoel");
        // Then - Post-condition
        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("guess is valid")
    void guessIsValid()
    {
        // Given - Pre-condition
        var feedback = new Feedback();

        // When - Action
        feedback.giveHint(".,.,.,.,.,.", "winkel", "winkel");
        //feedback.calculateMarks("winkel", "winkel");
        // Then - Post-condition
        assertTrue(feedback.guessIsValid("winkel"));
    }

    @Test
    @DisplayName("guess is not valid")
    void guessIsNotValid()
    {
        // Given - Pre-condition
        var feedback = new Feedback();

        // When - Action
        // Then - Post-condition
        assertFalse(feedback.guessIsValid("wiel"));
    }

    @Test
    @DisplayName("Marks test")
    void marksTest()
    {
        // Given - Pre-condition
        var feedback = new Feedback();

        // When - Action
        var result = feedback.calculateMarks("huis", "guit");

        // Then - Post-condition
        assertTrue(result.toString().equals("[ABSENT, CORRECT, CORRECT, ABSENT]"));
    }

    @Test
    @DisplayName("Guess correct word")
     void guessCorrectWord()
    {
        var game = new Game("winkel");
        var feedback = game.getCurrentRound().guessWord("winkel");

        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Guess wrong word")
    void guessWrongWord()
    {
        var game = new Game("winkel");
        var feedback = game.getCurrentRound().guessWord("winken");

        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Test feedback toString")
    void testToString()
    {
        // Given - Pre-condition

        // When - Action
        var feedback1 = new Feedback();
        var feedback2 = new Feedback();

        // Then - Post-condition
        assertTrue(feedback1.toString().equals(feedback2.toString()));
    }

    @Test
    @DisplayName("Test feedback equals")
    void testFeedbackEquals()
    {
        // Given - Pre-condition

        // When - Action
        var feedback1 = new Feedback();
        var feedback2 = new Feedback();

        // Then - Post-condition
        assertTrue(feedback1.equals(feedback2));
    }

    @MethodSource("provideHintExamples")
    static Stream<Arguments> provideHintExamples()
    {
        return Stream.of(
                Arguments.of(

                        new ArrayList<Character>(Arrays.asList('w', 'i', '.', '.', 'e', '.')),
                        new Feedback(),
                        "w,i,.,.,.,.",
                        "winkel",
                        "wassen"
                ),
                Arguments.of(

                        new ArrayList<Character>(Arrays.asList('w', '.', '.', '.', 'e', '.')),
                        new Feedback(),
                        "w,.,.,.,.,.",
                        "winkel",
                        "wassen"
                ),
                Arguments.of(

                        new ArrayList<Character>(Arrays.asList('w', '.', '.', 'k', 'e', '.')),
                        new Feedback(),
                        "w,.,.,.,.,.",
                        "winkel",
                        "werken"
                )
                );
    }


    @ParameterizedTest
    @MethodSource("provideHintExamples")
    @DisplayName("Test hint")
    void testHint(ArrayList<Character> charListToCompare, Feedback feedback, String previousHint, String wordToGuess, String attempt)
    {
        // When - Action

        System.out.println(feedback.giveHint(previousHint, wordToGuess, attempt)
                .toString()
                .replace(" ", ""));

        System.out.println(charListToCompare
                .toString()
                .replace(" ", ""));
        // Then - Post-condition
        assertTrue(
                feedback.giveHint(previousHint, wordToGuess, attempt)
                        .toString()
                        .replace(" ", "")
                .equals(charListToCompare
                        .toString()
                        .replace(" ", "")));
    }

    @Test
    @DisplayName("Test new game")
    public void testNewGame()
    {
        var game1 = new Game("huis");
        var result = game1.getCurrentRound().guessWord("huis");

        assertTrue(result.isWordGuessed());
    }

    @Test
    @DisplayName("Test new round at existing game")
    public void testNewGameRound()
    {
        System.out.println("test new game round");

        var game1 = new Game("huis");

        var feedbackFirstRound = game1.getCurrentRound().guessWord("huis");

        game1.newRound("auto");
        var feedbackSecondRound = game1.getCurrentRound().guessWord("huis");

        assertTrue(feedbackFirstRound.isWordGuessed() != feedbackSecondRound.isWordGuessed());
    }
}