package nl.hu.cisq1.lingo.lingoTrainer.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GameStatusTest {
    @Test
    void testValueOf() {
        assertEquals(GameStatus.STARTED, GameStatus.valueOf("STARTED"));
    }

    @Test
    void testValues() {
        GameStatus[] actualValuesResult = GameStatus.values();
        assertEquals(2, actualValuesResult.length);
        assertEquals(GameStatus.STARTED, actualValuesResult[0]);
        assertEquals(GameStatus.STOPPED, actualValuesResult[1]);
    }
}

