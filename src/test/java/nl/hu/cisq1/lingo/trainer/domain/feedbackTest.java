package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class feedbackTest {
    @Test
    @DisplayName("word is guessed if all letters are correct, This test should be able to determine if a word was guessed or not")
    public void wordIsGuessed(){
        Feedback test = new Feedback("woord", List.of(Feedback.Mark.CORRECT, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT));
        assertTrue(test.isWordGuessed());
    }

    @Test
    @DisplayName("word is guessed if all letters are correct, This test should be able to determine if a word was incorrect or not")
    public void wordIsNOTGuessed(){
        Feedback test = new Feedback("woord", List.of(Feedback.Mark.ABSENT, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT));
        assertFalse(test.isWordGuessed());
    }

    @ParameterizedTest
    @MethodSource("provideHintExamples")
    @DisplayName("a hint is given, This test should be able give hints")
    public void giveHint(String lastHint, String word, String answer, List<Feedback.Mark> FBList){
        Feedback testPos = new Feedback(word, FBList);

        String testHint = testPos.giveHint(lastHint);

        assertEquals(testHint,answer);
    }
    static Stream<Arguments> provideHintExamples() {
        return Stream.of(
                Arguments.of("D?????", "Dragon", "D???on", List.of(Feedback.Mark.CORRECT, Feedback.Mark.ABSENT,  Feedback.Mark.ABSENT,  Feedback.Mark.ABSENT, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT)),
                Arguments.of("D???on", "Dragon", "Dr??on", List.of(Feedback.Mark.CORRECT, Feedback.Mark.CORRECT, Feedback.Mark.ABSENT,  Feedback.Mark.ABSENT, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT)),
                Arguments.of("D???on", "Drago?", "D???on", List.of(Feedback.Mark.CORRECT, Feedback.Mark.CORRECT, Feedback.Mark.ABSENT,  Feedback.Mark.ABSENT, Feedback.Mark.CORRECT, Feedback.Mark.INVALID)),
                Arguments.of("D???on", "Dragon", "Dra?on", List.of(Feedback.Mark.CORRECT, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT, Feedback.Mark.ABSENT, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT))
        );}

}