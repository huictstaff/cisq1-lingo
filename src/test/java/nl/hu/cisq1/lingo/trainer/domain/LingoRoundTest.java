package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.constraints.AssertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LingoRoundTest {
    @Test
    @DisplayName("checkCorrect")
    public void checkRoundGuess(){
        System.out.println("checkCorrect");
        LingoRound round = new LingoRound("woord");
        assertEquals("woord",round.guess("woord"));
    }

    @Test
    @DisplayName("checkInvalid")
    public void checkRoundInvalid(){
        System.out.println("checkInvalid");
        LingoRound round = new LingoRound("woord");
        assertEquals("-----", round.guess("invalid"));
    }

    @Test
    @DisplayName("checkPresent")
    public void checkRoundPresent(){
        System.out.println("checkPresent");
        LingoRound round = new LingoRound("woord");
        List<Mark> marks = new ArrayList<>();
        int i = 0;
        while (i<5){
            marks.add(Mark.PRESENT);
            i+=1;
        }
        assertEquals(marks,round.getMarks("owrdo"));
    }

    public static Stream<Arguments> roundTest(){
        return Stream.of(
                Arguments.of(new LingoRound("banaan"), "banana", "bana--"),
                Arguments.of(new LingoRound("ksuir"), "kruis", "k-ui-"),
                Arguments.of(new LingoRound("kaasje"), "kastje", "ka--je"),
                Arguments.of(new LingoRound("aaabbb"), "bbbaaa", "------"),
                Arguments.of(new LingoRound("aaabab"), "bbbaaa", "----a-"),
                Arguments.of(new LingoRound("aaaaaa"), "bbbbbb", "------"),
                Arguments.of(new LingoRound("gehoor"), "onmens", "------"),
                Arguments.of(new LingoRound("aabbcc"), "abcabc", "a----c"),
                Arguments.of(new LingoRound("alianna"), "liniaal", "-------"),
                Arguments.of(new LingoRound("heren"), "haren", "h-ren"),
                Arguments.of(new LingoRound("eeaaae"), "aaeeae", "----ae")
        );
    }
    @ParameterizedTest
    @MethodSource({"roundTest"})
    @DisplayName("Hint given depends on the attempted word")
    void giveHint(LingoRound lingoRound, String attempt, String expectedHint){
        assertEquals(expectedHint,lingoRound.guess(attempt));
    }


}
