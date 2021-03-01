package nl.hu.cisq1.lingo.words.domain;

import nl.hu.cisq1.lingo.words.data.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordTest {

    @Test
    @DisplayName("length is based on given word")
    void lengthBasedOnWord() {
        Word word = new Word("woord");
        int length = word.getLength();
        assertEquals(5, length);
    }
}
