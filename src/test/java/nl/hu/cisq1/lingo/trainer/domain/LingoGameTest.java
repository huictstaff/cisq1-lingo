package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.enums.RoundType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LingoGameTest {
    LingoGame game;

    @BeforeEach
    void setUp() {
        this.game = new LingoGame();
    }

    @Test
    @DisplayName("Check if round gets added to allrounds")
    void newRound() {
        this.game.newRound("apple");
        assertEquals(List.of(new Round(RoundType.FIVELETTERS, "apple", this.game)), this.game.getAllRounds());
    }

    @Test
    @DisplayName("Generate type correctly")
    void generateType() {
        this.game.newRound("apple");
        assertEquals(RoundType.SIXLETTERS, this.game.generateType());
    }

    @Test
    @DisplayName("Get last round correctly")
    void getLastRound() {
        this.game.newRound("apple");
        assertEquals(new Round(RoundType.FIVELETTERS, "apple", this.game), this.game.getLastRound());
    }

    @Test
    @DisplayName("Add score works correctly")
    void addScore() {
        this.game.addScore(500);
        assertEquals(500, this.game.getScore());
    }

    @Test
    @DisplayName("get score works correctly")
    void getScore() {
        assertEquals(0, this.game.getScore());
    }

    @Test
    @DisplayName("Get all rounds works correctly")
    void getAllRounds() {
        this.game.newRound("apple");
        assertEquals(List.of(new Round(RoundType.FIVELETTERS, "apple", this.game)), this.game.getAllRounds());
    }

    @Test
    @DisplayName("get last rounds throws error when allrounds is empty")
    void getLastRoundThrows() {
        assertThrows(RuntimeException.class, () -> this.game.getLastRound());
    }
}