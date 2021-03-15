package trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {

    @Test
    @DisplayName("Word is guessed when the returned hint is the same as the word")
    public void guessWord() {
        Round round = new Round("woord");
        assertEquals(List.of("w", "o", "o", "r", "d"), round.guessWord("woord"));
        assertNull(round.guessWord(""));
    }
}