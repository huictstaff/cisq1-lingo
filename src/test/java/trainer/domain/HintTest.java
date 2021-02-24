package trainer.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HintTest {

    @Test
    public void lettersOfHint() {
        Hint hint = new Hint(List.of('w', 'o', '.', '.', '.'));

        assertEquals(List.of('w', 'o'), hint.lettersOfHint());
    }


    @Test
    public void getNextHintIndex() {
        Hint hint = new Hint(List.of('w', 'o', '.', '.', '.'));

        assertEquals(2, hint.getNextHintIndex());
    }
}