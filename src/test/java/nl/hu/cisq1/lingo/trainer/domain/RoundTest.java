package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    @Test
    @DisplayName("A round is made and a feedback is given based on the attempted word")
    public void connectingFeedbackWithRound(){
        Round test = new Round ("Grond");
        Feedback testFB = test.giveFeedBack("Gangs");
        assertEquals(testFB.toString(),"word attempted: Gangs Marks: CORRECT ABSENT PRESENT ABSENT ABSENT Hints: G???? ");
    }

    @ParameterizedTest
    @MethodSource("provideFeedBackExamples")
    @DisplayName("a hint is given, This test should be able give hints")
    public void giveHint(String rightWord, String guessWord, String answer){
        Round test = new Round (rightWord);
        Feedback testFB = test.giveFeedBack(guessWord);

        assertEquals(testFB.toString(), answer);
    }

    static Stream<Arguments> provideFeedBackExamples() {
        return Stream.of(
                Arguments.of("Grond", "Grond",  "word attempted: Grond Marks: CORRECT CORRECT CORRECT CORRECT CORRECT Hints: Grond "),
                Arguments.of("Grond", "Gronds", "word attempted: Gronds Marks: CORRECT CORRECT CORRECT CORRECT CORRECT Hints: G???? "),
                Arguments.of("Grond", "Angel",  "word attempted: Angel Marks: CORRECT CORRECT CORRECT CORRECT CORRECT Hints: G???? "),
                Arguments.of("Grond", "Grdon",  "word attempted: Grdon Marks: CORRECT CORRECT CORRECT CORRECT CORRECT Hints: Gr??? "),
                Arguments.of("Grond", "DonrG",  "word attempted: DonrG Marks: CORRECT CORRECT CORRECT CORRECT CORRECT Hints: G???? "));
    }
}