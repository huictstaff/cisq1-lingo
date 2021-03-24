package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidCharacterException;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidGuessLengthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class FeedbackTest {

    @Nested
    @DisplayName("Word guessing")
    class Guessing {

        @Test
        @DisplayName("Word is guessed if all letters are correct")
        void wordIsGuessed() {
            Feedback feedback = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), "atmpt");
            assertTrue(feedback.isWordGuessed());
        }

        @Test
        @DisplayName("Word is not guessed if not all letters are correct")
        void wordIsNotGuessed() {
            Feedback feedback = new Feedback(List.of(Mark.PRESENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), "atmpt");
            assertFalse(feedback.isWordGuessed());
        }
    }

    @Nested
    @DisplayName("Guess validation")
    class GuessValidation {

        @Test
        @DisplayName("Guess is invalid when one or more characters are invalid")
        void guessIsInvalid() {
            assertThrows(InvalidCharacterException.class, () -> new Feedback(List.of(Mark.INVALID, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), "atmpt"));
        }

        @Test
        @DisplayName("Guess is valid when there are no invalid characters")
        void guessIsNotInvalid() {
            Feedback feedback = new Feedback(List.of(Mark.PRESENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), "atmpt");
            assertTrue(feedback.isGuessValid(feedback.getMarks()));
        }

        @Test
        @DisplayName("Constructor will throw exception if guess doens't match marklist size")
        void guessLengthNotValid() {
            assertThrows(InvalidGuessLengthException.class, () -> new Feedback(List.of(Mark.PRESENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), "attempt"));
        }
    }

    // @TestInstance per class prevents us from having to declare the whole class static
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    @DisplayName("FeedbackRepresentation")
    class FeedbackRepresentation {

        @Test
        @DisplayName("Feedback is presented in a Treemap")
        void feedbackInTreemap() {
            Feedback feedback = new Feedback(List.of(Mark.PRESENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), "kenel");
            assertEquals("{0={k=CORRECT}, 1={e=CORRECT}, 2={n=ABSENT}, 3={e=CORRECT}, 4={l=CORRECT}}", feedback.prepareFeedback("kerel", "kenel").toString());
        }

        @Test
        @DisplayName("Feedback is correct with multiple present characters")
        void feedbackMultipleChars() {
            Feedback feedback = new Feedback(List.of(Mark.PRESENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), "atmpt");
            assertEquals("{0={t=CORRECT}, 1={i=CORRECT}, 2={v=CORRECT}, 3={o=CORRECT}, 4={l=CORRECT}, 5={o=ABSENT}}", feedback.prepareFeedback("tivoli", "tivolo").toString());
            assertEquals("{0={t=CORRECT}, 1={o=ABSENT}, 2={v=CORRECT}, 3={o=CORRECT}, 4={l=CORRECT}, 5={o=ABSENT}}", feedback.prepareFeedback("tivoli", "tovolo").toString());
        }

        @Test
        @DisplayName("Feedback is presented in a arrayList of marks")
        void feedbackInMarkArray() {
            Feedback feedback = new Feedback(List.of(Mark.PRESENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), "kenel");
            assertEquals(List.of(Mark.CORRECT, Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT), feedback.toMarkArray(feedback.prepareFeedback("kerel", "kenel")));
        }

        @Test
        @DisplayName("Feedback is is presented in a list of chars")
        void feedbackcharArrayList() {
            Feedback feedback = new Feedback(List.of(Mark.PRESENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), "kenel");
            assertEquals(List.of('k', 'e', 'n', 'e', 'l'), feedback.toCharArrayList(feedback.prepareFeedback("kerel", "kenel")));
        }

        @ParameterizedTest
        @MethodSource("hintExamples")
        @DisplayName("Show the right chars when giving a hint")
        void giveHint(String word, List<Mark> marks, Hint previousHint, Hint newHint) {
            Feedback feedback = new Feedback(marks, "Droplul");
            assertEquals(newHint.getHint(), feedback.giveHint(previousHint, word).getHint());
        }

        private Stream<Arguments> hintExamples() {
            return Stream.of(
                    Arguments.of(
                            "droplul",
                            List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT),
                            new Hint(List.of('.', '.', '.', '.', '.', '.', '.')),
                            new Hint(List.of('d', 'r', 'o', 'p', 'l', 'u', 'l'))),

                    Arguments.of(
                            "droplul",
                            List.of(Mark.CORRECT, Mark.PRESENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT),
                            new Hint(List.of('.', '.', '.', '.', '.', '.', '.')),
                            new Hint(List.of('d', '+', '-', '-', '-', '-', '-'))),

                    Arguments.of(
                            "droplul",
                            List.of(Mark.CORRECT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT),
                            new Hint(List.of('.', 'e', '.', '.', '.', '.', '.')),
                            new Hint(List.of('d', '-', '-', '-', '-', '-', '-'))),

                    Arguments.of(
                            "droplul",
                            List.of(Mark.CORRECT, Mark.PRESENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT),
                            new Hint(List.of('.', 'o', '.', '.', '.', '.', '.')),
                            new Hint(List.of('d', '+', '-', '-', '-', '-', '-')))
            );
        }
    }
}