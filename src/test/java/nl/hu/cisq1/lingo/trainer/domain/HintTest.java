package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HintTest {
    @Test
    void Hint(){
        Hint hint = new Hint(List.of("w", "o", "o", "r", "d"),
                List.of(Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT));
        assertEquals(List.of("w", "o", "o", "r", "d"), hint.getHintStrings());
        assertEquals(List.of(Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT), hint.getMarks());
    }

}