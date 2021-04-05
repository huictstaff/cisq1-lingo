package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidCharacterException;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidGuessLengthException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.*;
import static org.junit.jupiter.api.Assertions.*;


class FeedbackTest {

    @Nested
    @DisplayName("Word guessing")
    class Guessing {

        @Test
        @DisplayName("Word is guessed if all letters are correct")
        void wordIsGuessed() {
            Feedback feedback = new Feedback(List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT), "atmpt");
            assertTrue(feedback.isWordGuessed());
        }

        @Test
        @DisplayName("Word is not guessed if not all letters are correct")
        void wordIsNotGuessed() {
            Feedback feedback = new Feedback(List.of(Mark.PRESENT, CORRECT, CORRECT, CORRECT, CORRECT), "atmpt");
            assertFalse(feedback.isWordGuessed());
        }
    }

    @Nested
    @DisplayName("Guess validation")
    class GuessValidation {

        @Test
        @DisplayName("Guess is invalid when one or more characters are invalid")
        void guessIsInvalid() {
            try {
                List<Mark> markList = List.of(INVALID, CORRECT, CORRECT, CORRECT, CORRECT);
                assertThrows(InvalidCharacterException.class, () -> new Feedback(markList, "atmpt"));
            } catch (Exception e) {
                assertNull(e);
            }
        }

        @Test
        @DisplayName("InvalidCharacterException if feedbacklist is null")
        void emtpyFeedbackValidation() {
            try {
                ArrayList<Mark> arrayList = new ArrayList<>();
                assertThrows(InvalidCharacterException.class, () -> new Feedback(arrayList, "woord"));
            } catch (Exception e) {
                assertNull(e);
            }
        }

        @Test
        @DisplayName("Guess is valid when there are no invalid characters")
        void guessIsNotInvalid() {
            try {
                List<Mark> markList = List.of(Mark.PRESENT, CORRECT, CORRECT, CORRECT, CORRECT);
                Feedback feedback = new Feedback(markList, "atmpt");
                assertTrue(feedback.isGuessValid(feedback.getMarks()));
            } catch (Exception e) {
                assertNull(e);
            }
        }

        @Test
        @DisplayName("Constructor will throw exception if guess doens't match marklist size")
        void guessLengthNotValid() {
            try {
                List<Mark> markList = List.of(Mark.PRESENT, CORRECT, CORRECT, CORRECT, CORRECT);
                assertThrows(InvalidGuessLengthException.class, () -> new Feedback(markList, "attempt"));
            } catch (Exception e) {
                assertNull(e);
            }
        }
    }

    // @TestInstance per class prevents us from having to declare the whole class static
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    @DisplayName("FeedbackRepresentation")
    class FeedbackRepresentation {

        @Test
        @DisplayName("Set marks")
        void setMarks() {
            Feedback feedback = new Feedback(List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT, CORRECT, CORRECT), "attempp");
            feedback.setMarks(List.of(ABSENT, ABSENT, ABSENT, ABSENT, ABSENT, ABSENT, CORRECT));
            assertEquals(List.of(ABSENT, ABSENT, ABSENT, ABSENT, ABSENT, ABSENT, CORRECT), feedback.getMarks());
        }

        @Test
        @DisplayName("Feedback is presented in a arrayList of marks")
        void feedbackInMarkArray() {
            Feedback feedback = new Feedback(List.of(Mark.PRESENT, CORRECT, CORRECT, CORRECT, CORRECT), "kenel");
            assertEquals(List.of(CORRECT, CORRECT, ABSENT, CORRECT, CORRECT), feedback.toMarkArray(feedback.prepareFeedback("kerel", "kenel")));
        }

        @Test
        @DisplayName("Feedback is is presented in a list of chars")
        void feedbackcharArrayList() {
            Feedback feedback = new Feedback(List.of(Mark.PRESENT, CORRECT, CORRECT, CORRECT, CORRECT), "kenel");
            assertEquals(List.of('k', 'e', 'n', 'e', 'l'), feedback.toCharArrayList(feedback.prepareFeedback("kerel", "kenel")));
        }

        @ParameterizedTest
        @MethodSource("hintExamples")
        @DisplayName("Show the right chars when giving a hint")
        void giveHint(String word, List<Mark> marks, Hint previousHint, Hint newHint) {
            Feedback feedback = new Feedback(marks, "Droplul");
            assertEquals(newHint.getHint(), feedback.giveHint(previousHint, word).getHint());
        }

        @ParameterizedTest
        @MethodSource("giveFeedbackExamples")
        @DisplayName("Feedback is correct with multiple present characters")
        void feedbackMultipleChars(List<Mark> expected, List<Mark> actual) {
            assertEquals(expected, actual);
        }

        private Stream<Arguments> hintExamples() {
            return Stream.of(
                    Arguments.of(
                            "droplul",
                            List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT, CORRECT, CORRECT),
                            new Hint(List.of('.', '.', '.', '.', '.', '.', '.')),
                            new Hint(List.of('d', 'r', 'o', 'p', 'l', 'u', 'l'))),

                    Arguments.of(
                            "droplul",
                            List.of(CORRECT, Mark.PRESENT, ABSENT, ABSENT, ABSENT, ABSENT, ABSENT),
                            new Hint(List.of('.', '.', '.', '.', '.', '.', '.')),
                            new Hint(List.of('d', '+', '-', '-', '-', '-', '-'))),

                    Arguments.of(
                            "droplul",
                            List.of(CORRECT, ABSENT, ABSENT, ABSENT, ABSENT, ABSENT, ABSENT),
                            new Hint(List.of('.', 'e', '.', '.', '.', '.', '.')),
                            new Hint(List.of('d', '-', '-', '-', '-', '-', '-'))),

                    Arguments.of(
                            "droplul",
                            List.of(CORRECT, PRESENT, ABSENT, ABSENT, ABSENT, ABSENT, ABSENT),
                            new Hint(List.of('.', 'o', '.', '.', '.', '.', '.')),
                            new Hint(List.of('d', '+', '-', '-', '-', '-', '-'))),

                    Arguments.of(
                            "droplul",
                            List.of(CORRECT, PRESENT, ABSENT, ABSENT, ABSENT, ABSENT, PRESENT),
                            new Hint(List.of('.', 'o', '.', '.', '.', '.', '.')),
                            new Hint(List.of('d', '+', '-', '-', '-', '-', '+')))
            );
        }

        private Stream<Arguments> giveFeedbackExamples() {
            return Stream.of(
                    Arguments.of(
                            List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT, ABSENT),
                            new Feedback("tivolo").toMarkArray(new Feedback("tivolo").prepareFeedback("tivoli", "tivolo"))),

                    Arguments.of(
                            List.of(CORRECT, ABSENT, CORRECT, CORRECT, CORRECT, ABSENT),
                            new Feedback("tovolo").toMarkArray(new Feedback("tovolo").prepareFeedback("tivoli", "tovolo"))),

                    Arguments.of(
                            List.of(CORRECT, ABSENT, ABSENT, CORRECT, CORRECT, PRESENT),
                            new Feedback("torolv").toMarkArray(new Feedback("torolv").prepareFeedback("tivoli", "torolv"))),

                    Arguments.of(
                            List.of(CORRECT, PRESENT, CORRECT, PRESENT, CORRECT, ABSENT),
                            new Feedback("tovilo").toMarkArray(new Feedback("tovilo").prepareFeedback("tivoli", "tovilo"))),

                    Arguments.of(
                            List.of(CORRECT, PRESENT, CORRECT, PRESENT, CORRECT, ABSENT),
                            new Feedback("tovilz").toMarkArray(new Feedback("tovilz").prepareFeedback("tivoli", "tovilz")))
            );
        }
    }
}