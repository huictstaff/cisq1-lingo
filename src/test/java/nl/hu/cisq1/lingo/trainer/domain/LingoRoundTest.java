package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.AssertTrue;

import java.util.ArrayList;
import java.util.List;

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



}
