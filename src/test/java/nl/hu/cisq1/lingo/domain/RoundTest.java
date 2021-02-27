package nl.hu.cisq1.lingo.domain;

import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;


class RoundTest {

    @Test
    @DisplayName("Start round and first letter of word is shown")
    void showFirstLetterOfWord() {
        Word word = new Word("woord");

        Round round = new Round(1,  word, new ArrayList<>());

        assertEquals(Arrays.asList('w', '_', '_', '_', '_'), round.startRound());
    }
}