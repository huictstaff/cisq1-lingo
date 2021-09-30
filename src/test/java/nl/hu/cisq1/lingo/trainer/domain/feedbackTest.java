package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class feedbackTest {
    private Feedback testNeg;
    private Feedback testPos;

    @BeforeEach
    public void init() {
        testNeg = new Feedback("woord", List.of(Feedback.Mark.INVALID, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT));
        testPos = new Feedback("Dragon", List.of(Feedback.Mark.CORRECT, Feedback.Mark.ABSENT, Feedback.Mark.ABSENT, Feedback.Mark.ABSENT, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT));
    }

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

//    @Test
//    @DisplayName("word is guessed if all letters are correct, This test should be able to determine if a word was incorrect or not")
//    public void wordIXsNOTGuessed(){
//        String pop = "pop";
//        assertTrue(pop.contains("p"));
//    }

    @ParameterizedTest
    @MethodSource("provideHintExamples")
    @DisplayName("a hint is given, This test should be able give hints")
    public void giveHint(String vorigeHint, String wordToGuess, List<Feedback.Mark> FBList, String answer){
        assertEquals(testPos.giveHint(vorigeHint, wordToGuess, FBList),answer);
    }
    static Stream<Arguments> provideHintExamples() {
        return Stream.of(
                Arguments.of("D?????", "Dragon", List.of(Feedback.Mark.CORRECT, Feedback.Mark.ABSENT, Feedback.Mark.ABSENT, Feedback.Mark.ABSENT, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT),"D???on"),
                Arguments.of("D???on", "Dragon", List.of(Feedback.Mark.CORRECT, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT, Feedback.Mark.ABSENT, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT),"Dra?on")
        );}

}