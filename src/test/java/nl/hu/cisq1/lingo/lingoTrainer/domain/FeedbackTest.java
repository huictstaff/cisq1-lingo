package nl.hu.cisq1.lingo.lingoTrainer.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
    @DisplayName("Marks test")
    void marksTest2()
    {
        // Given - Pre-condition
        var feedback = new Feedback();

        // When - Action
        var result = feedback.calculateMarks("h,u,i,.", "h,u,i,.");

        // Then - Post-condition
        assertTrue(result.toString().equals("[CORRECT, CORRECT, CORRECT, ABSENT]"));
    }

    @Test
    @DisplayName("Marks test null")
    void marksTest3()
    {
        // Given - Pre-condition
        var feedback = new Feedback();
        feedback.setMarks(null);

        // When - Action
        var result = feedback.calculateMarks("h,u,i,.", "h,u,i,.");

        // Then - Post-condition
        assertTrue(result.toString().equals("[CORRECT, CORRECT, CORRECT, ABSENT]"));
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

        // Then - Post-condition
        assertEquals(feedback.giveHint(previousHint, wordToGuess, attempt),(charListToCompare));
    }

    @Test
    void testConstructor() {
        Feedback actualFeedback = new Feedback();
        ArrayList<Mark> markList = new ArrayList<Mark>();
        actualFeedback.setMarks(markList);
        assertSame(markList, actualFeedback.getMarks());
        assertEquals("Feedback{attempt='null', mark=[]}", actualFeedback.toString());
    }

    @Test
    void testIsWordGuessed() {
        assertTrue((new Feedback()).isWordGuessed());
    }

    @Test
    void testIsWordGuessed2() {
        ArrayList<Mark> markList = new ArrayList<Mark>();
        markList.add(Mark.INVALID);

        Feedback feedback = new Feedback();
        feedback.setMarks(markList);
        assertFalse(feedback.isWordGuessed());
    }

    @Test
    void testIsWordGuessed3() {
        ArrayList<Mark> markList = new ArrayList<Mark>();
        markList.add(Mark.CORRECT);

        Feedback feedback = new Feedback();
        feedback.setMarks(markList);
        assertTrue(feedback.isWordGuessed());
    }

    @Test
    void testIsWordGuessed4() {
        ArrayList<Mark> markList = new ArrayList<Mark>();
        markList.add(Mark.CORRECT);
        markList.add(Mark.INVALID);

        Feedback feedback = new Feedback();
        feedback.setMarks(markList);
        assertFalse(feedback.isWordGuessed());
    }

    @Test
    void testGuessIsValid() {
        assertTrue((new Feedback()).guessIsValid("Attempt"));
        assertFalse((new Feedback()).guessIsValid("42"));
    }

    @Test
    void testGiveHint() {
        Feedback feedback = new Feedback();
        List<Character> actualGiveHintResult = feedback.giveHint("Previous Hint", "42", "Attempt");
        assertEquals(2, actualGiveHintResult.size());
        assertEquals('P', actualGiveHintResult.get(0));
        assertEquals('r', actualGiveHintResult.get(1));
        ArrayList<Mark> marks = feedback.getMarks();
        assertEquals(2, marks.size());
        assertEquals(Mark.ABSENT, marks.get(0));
        assertEquals(Mark.ABSENT, marks.get(1));
    }

    @Test
    void testGiveHint2() {
        Feedback feedback = new Feedback();
        List<Character> actualGiveHintResult = feedback.giveHint("nl.hu.cisq1.lingo.lingoTrainer.domain.Feedback", "42",
                "Attempt");
        assertEquals(2, actualGiveHintResult.size());
        assertEquals('n', actualGiveHintResult.get(0));
        assertEquals('l', actualGiveHintResult.get(1));
        ArrayList<Mark> marks = feedback.getMarks();
        assertEquals(2, marks.size());
        assertEquals(Mark.ABSENT, marks.get(0));
        assertEquals(Mark.ABSENT, marks.get(1));
    }

    @Test
    void testGiveHint3() {
        Feedback feedback = new Feedback();
        List<Character> actualGiveHintResult = feedback.giveHint("nl.hu.cisq1.lingo.lingoTrainer.domain.Feedback",
                "Word To Guess", "nl.hu.cisq1.lingo.lingoTrainer.domain.Feedback");
        assertEquals(13, actualGiveHintResult.size());
        assertEquals('n', actualGiveHintResult.get(0));
        assertEquals('l', actualGiveHintResult.get(1));
        assertEquals('.', actualGiveHintResult.get(2));
        assertEquals('h', actualGiveHintResult.get(3));
        assertEquals('u', actualGiveHintResult.get(4));
        assertEquals('.', actualGiveHintResult.get(5));
        assertEquals('i', actualGiveHintResult.get(7));
        assertEquals('s', actualGiveHintResult.get(8));
        assertEquals('q', actualGiveHintResult.get(9));
        assertEquals('1', actualGiveHintResult.get(10));
        assertEquals('.', actualGiveHintResult.get(11));
        assertEquals('l', actualGiveHintResult.get(12));
        ArrayList<Mark> marks = feedback.getMarks();
        assertEquals(13, marks.size());
        assertEquals(Mark.ABSENT, marks.get(0));
        assertEquals(Mark.ABSENT, marks.get(1));
        assertEquals(Mark.ABSENT, marks.get(2));
        assertEquals(Mark.ABSENT, marks.get(10));
        assertEquals(Mark.ABSENT, marks.get(11));
        assertEquals(Mark.ABSENT, marks.get(12));
    }

    @Test
    void testGiveHint4() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> (new Feedback()).giveHint("42", "Word To Guess", "nl.hu.cisq1.lingo.lingoTrainer.domain.Feedback"));
    }

    @Test
    void testGiveHint5() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new Feedback()).giveHint("Previous Hint",
                "nl.hu.cisq1.lingo.lingoTrainer.domain.Feedback", "nl.hu.cisq1.lingo.lingoTrainer.domain.Feedback"));
    }

    @Test
    void testGiveHint6() {
        Feedback feedback = new Feedback();
        assertTrue(feedback.giveHint(",", "", "Attempt").isEmpty());
        assertTrue(feedback.getMarks().isEmpty());
    }

    @Test
    void testCalculateMarks() {
        Feedback feedback = new Feedback();
        List<Mark> actualCalculateMarksResult = feedback.calculateMarks("42", "Attempt");
        assertSame(feedback.marks, actualCalculateMarksResult);
        assertEquals(2, actualCalculateMarksResult.size());
        assertEquals(Mark.ABSENT, actualCalculateMarksResult.get(0));
        assertEquals(Mark.ABSENT, actualCalculateMarksResult.get(1));
    }

    @Test
    void testCalculateMarks2() {
        Feedback feedback = new Feedback();
        List<Mark> actualCalculateMarksResult = feedback.calculateMarks("nl.hu.cisq1.lingo.lingoTrainer.domain.Feedback",
                "nl.hu.cisq1.lingo.lingoTrainer.domain.Feedback");
        assertSame(feedback.marks, actualCalculateMarksResult);
        assertEquals(46, actualCalculateMarksResult.size());
        assertEquals(Mark.CORRECT, actualCalculateMarksResult.get(0));
        assertEquals(Mark.CORRECT, actualCalculateMarksResult.get(1));
        assertEquals(Mark.ABSENT, actualCalculateMarksResult.get(2));
        assertEquals(Mark.CORRECT, actualCalculateMarksResult.get(3));
        assertEquals(Mark.CORRECT, actualCalculateMarksResult.get(4));
        assertEquals(Mark.ABSENT, actualCalculateMarksResult.get(5));
        assertEquals(Mark.CORRECT, actualCalculateMarksResult.get(40));
        assertEquals(Mark.CORRECT, actualCalculateMarksResult.get(41));
        assertEquals(Mark.CORRECT, actualCalculateMarksResult.get(42));
        assertEquals(Mark.CORRECT, actualCalculateMarksResult.get(43));
        assertEquals(Mark.CORRECT, actualCalculateMarksResult.get(44));
        assertEquals(Mark.CORRECT, actualCalculateMarksResult.get(45));
    }

    @Test
    void testEquals() {
        assertFalse((new Feedback()).equals(null));
        assertFalse((new Feedback()).equals("Different type to Feedback"));
        assertFalse((new Feedback()).equals(null));
    }

    @Test
    void testEquals2() {
        Feedback feedback = new Feedback();
        assertTrue(feedback.equals(feedback));
        int expectedHashCodeResult = feedback.hashCode();
        assertEquals(expectedHashCodeResult, feedback.hashCode());
    }

    @Test
    void testEquals3() {
        Feedback feedback = new Feedback();
        Feedback feedback1 = new Feedback();
        assertTrue(feedback.equals(feedback1));
        int notExpectedHashCodeResult = feedback.hashCode();
        assertFalse(Objects.equals(notExpectedHashCodeResult, feedback1.hashCode()));
    }

    @Test
    void testEquals4() {
        Feedback feedback = new Feedback();
        feedback.setMarks(null);
        assertFalse(feedback.equals(new Feedback()));
    }
}

