package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.*;
import static org.junit.jupiter.api.Assertions.*;

class RoundTest {

    @Test
    @DisplayName("word is guessed if all letters are correct")
    void guessWord() {
        Round round = new Round("aapje");
        assertEquals(List.of(CORRECT, WRONG, PRESENT, WRONG, WRONG), round.guess("arend").getMarks());
        assertEquals(List.of(CORRECT, PRESENT, CORRECT, PRESENT, WRONG), round.guess("appel").getMarks());
        assertEquals(List.of(ILLEGAL, ILLEGAL, ILLEGAL, ILLEGAL, ILLEGAL), round.guess("beren").getMarks());
//        assertEquals(List.of(ILLEGAL, ILLEGAL, ILLEGAL, ILLEGAL, ILLEGAL), round.guess("adkea").getMarks());
        assertEquals(List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT), round.guess("aapje").getMarks());
    }

}