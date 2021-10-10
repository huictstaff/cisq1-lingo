package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    @Test
    @DisplayName("A round is made and a feedback is given based on the attempted word")
    public void connectingFeedbackWithRound(){
        Round test = new Round ("Grond");
        Feedback testFB = test.giveFeedBack("Gangs");
        assertEquals(testFB.toString(),"word attempted: Gangs Marks: [CORRECT, ABSENT, PRESENT, ABSENT, ABSENT] Hint: G?!??");
    }

    @ParameterizedTest
    @MethodSource("provideFeedBackExamples")
    @DisplayName("a hint is given, This test should be able give hints")
    public void giveHint(String rightWord, String guessWord, String answer){
        Round test = new Round (rightWord);
        Feedback testFB = test.giveFeedBack(guessWord);
        testFB.giveHint(guessWord);
        assertEquals(testFB.toString(), answer);
    }

    static Stream<Arguments> provideFeedBackExamples() {
        return Stream.of(
                Arguments.of("Grond", "Grond",  "word attempted: Grond Marks: [CORRECT, CORRECT, CORRECT, CORRECT, CORRECT] Hint: GROND"),
                Arguments.of("Grond", "Gronds", "word attempted: Gronds Marks: [INVALID] Hint: G????"),
                Arguments.of("Grond", "Angel",  "word attempted: Angel Marks: [ABSENT, PRESENT, PRESENT, ABSENT, ABSENT] Hint: ?!!??"),
                Arguments.of("Grond", "Grdon",  "word attempted: Grdon Marks: [CORRECT, CORRECT, PRESENT, ABSENT, PRESENT] Hint: GR!?!"),
                Arguments.of("Grond", "DonrG",  "word attempted: DonrG Marks: [ABSENT, PRESENT, PRESENT, ABSENT, ABSENT] Hint: ?!!??"),
                Arguments.of("Pope",  "Peep",   "word attempted: Peep Marks: [CORRECT, PRESENT, ABSENT, PRESENT] Hint: P!?!"));
    }

    //TODO: ask HUGO how i can test this or if i can at all
//    @Test
//    @DisplayName("Max attempts already reached, This test should fail cause the attempts are over 5")
//    public void gameOver(){
//        Round test = new Round ("Grond");
//        Feedback testFB1 = test.giveFeedBack("Gangs");
//        Feedback testFB2 = test.giveFeedBack("Gangs");
//        Feedback testFB3 = test.giveFeedBack("Gangs");
//        Feedback testFB4 = test.giveFeedBack("Gangs");
//        Feedback testFB5 = test.giveFeedBack("Gangs");
//        Feedback testFB6 = test.giveFeedBack("Gangs");
//
//        assertEquals(testFB6.toString(),"dsd");
//    }
}