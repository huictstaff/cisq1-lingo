package nl.hu.cisq1.lingo.trainer.presentation.DTO;

import nl.hu.cisq1.lingo.trainer.domain.Hint;
import nl.hu.cisq1.lingo.trainer.domain.Mark;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HintDTOTest {

    @Test
    void setHintStrings() {
        HintDTO hintDTO = new HintDTO();
        hintDTO.setHintStrings(List.of("w", "o", "o", "r", "d"));
        assertEquals(List.of("w", "o", "o", "r", "d"), hintDTO.getHintStrings());
    }

    @Test
    void setMarks() {
        HintDTO hintDTO = new HintDTO();
        hintDTO.setMarks(List.of(Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT));
        assertEquals(List.of(Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT), hintDTO.getMarks());
    }

    @Test
    void testToString() {
        HintDTO hintDTO = new HintDTO();
        assertNotNull(hintDTO.toString());
    }
}