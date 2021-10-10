package nl.hu.cisq1.lingo.lingoTrainer.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MarkTest {
    @Test
    void testValueOf() {
        assertEquals(Mark.ABSENT, Mark.valueOf("ABSENT"));
    }

    @Test
    void testValues() {
        Mark[] actualValuesResult = Mark.values();
        assertEquals(4, actualValuesResult.length);
        assertEquals(Mark.INVALID, actualValuesResult[0]);
        assertEquals(Mark.ABSENT, actualValuesResult[1]);
        assertEquals(Mark.PRESENT, actualValuesResult[2]);
        assertEquals(Mark.CORRECT, actualValuesResult[3]);
    }
}

