package nl.hu.cisq1.lingo.lingoTrainer.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class WordTest {
    @Test
    void testConstructor() {
        Word actualWord = new Word();
        assertNull(actualWord.getLength());
        assertNull(actualWord.getValue());
    }

    @Test
    void testConstructor2() {
        Word actualWord = new Word("Word");
        assertEquals(4, actualWord.getLength().intValue());
        assertEquals("Word", actualWord.getValue());
    }
}

