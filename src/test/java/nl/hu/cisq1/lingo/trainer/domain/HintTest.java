package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.HintException;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HintTest {

    @Test
    public void firstHintOfAWord() {
        Word word = new Word("woord");
        Hint hint = Hint.firstHintof(word);
        assertEquals(new Hint(List.of('W', '.', '.', '.', '.')), hint);
    }



    @Test
    public void hintOffOfAPreviousHint() {
        Word word = new Word("woord");
        assertEquals(new Hint(List.of('W', '.', '.', '.', '.')), Hint.of(new Hint(List.of('.', '.', '.', '.', '.')), word));
        assertEquals(new Hint(List.of('W', 'O', '.', '.', '.')), Hint.of(new Hint(List.of('W', '.', '.', '.', '.')), word));
        assertEquals(new Hint(List.of('W', 'O', 'O', 'R', 'D')), Hint.of(new Hint(List.of('W', 'O', 'O', 'R', '.')), word));
    }

    @Test
    public void hintWithConflictingPreviousHintAndWordLengths() {
        Word word = new Word("woord"); // 5 letters
                                                                       //1,   2,   3,   4,   5,   6  letters
        assertThrows(HintException.class, ()-> Hint.of(new Hint(List.of('W', '.', '.', '.', '.', '.')), word));
    }

    @Test
    public void hintWithAPreviousHintOfAGuessedWord() {
        Word word = new Word("woord");
        assertThrows(HintException.class, ()-> Hint.of(new Hint(List.of('W', 'O', 'O', 'R', 'D')), word));
    }

}