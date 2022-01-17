package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoundTest {

    @Test
    @DisplayName("Valid guess is made")
    void guess(){
        Round round = mock(Round.class);
        doNothing().when(round).guess(isA(String.class));
        round.guess("woord");

        verify(round).guess("woord");

    }

    @ParameterizedTest
    @DisplayName("gives correct hints")
    @MethodSource("giveHintExamples")
    void giveHint(String wordToguess, Feedback feedback, String expectedHint) throws Exception {
        Round round = new Round(wordToguess,0);
        round.addFeedback(feedback);
        assertEquals(expectedHint, round.giveHint());
    }

    static Stream<Arguments> giveHintExamples() {
        return Stream.of(
                Arguments.of("woord",new Feedback("waard", List.of(Mark.Correct,Mark.Absent,Mark.Absent,Mark.Correct,Mark.Correct)),"w..rd"),
                Arguments.of("woord",new Feedback("woerd",List.of(Mark.Correct,Mark.Correct,Mark.Absent,Mark.Correct,Mark.Correct)),"wo.rd"),
                Arguments.of("woord",new Feedback("woord",List.of(Mark.Correct,Mark.Correct,Mark.Correct,Mark.Correct,Mark.Correct)),"woord")

        );
    }

}
