package nl.hu.cisq1.lingo.trainer.domain.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {
    @Test
    public void convertACharlistToAString() {
        assertEquals("woord", Utils.charsToString(List.of('w', 'o', 'o', 'r', 'd')));
    }

    @Test
    public void convertAnEmptyCharlistToAString() {
        assertEquals("", Utils.charsToString(List.of()));
    }


    @Test
    public void nullInputInsteadOfList() {
        assertThrows(IllegalArgumentException.class, () -> Utils.charsToString(null));
    }

}