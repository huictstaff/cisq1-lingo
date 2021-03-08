package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.enums.RoundType;
import nl.hu.cisq1.lingo.trainer.exception.RoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LingoGameTest {
    private LingoGame game;

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
    @DisplayName("Generate type correctly on round 1")
    void generateTypeRoundOne() {
        this.game.newRound("apple");
        assertEquals(RoundType.FIVELETTERS, this.game.getAllRounds().get(this.game.getAllRounds().size()-1).getType());
    }

    @Test
    @DisplayName("Generate type correctly on round 2")
    void generateTypeRoundTwo() {
        this.game.newRound("apple");
        assertEquals(RoundType.SIXLETTERS, this.game.generateType());
    }

    @Test
    @DisplayName("Generate type correctly on round 3")
    void generateTypeRoundThree() {
        this.game.newRound("apple");
        this.game.newRound("banana");
        assertEquals(RoundType.SEVENLETTERS, this.game.generateType());
    }

    @Test
    @DisplayName("Get last round correctly")
    void getLastRound() {
        this.game.newRound("apple");
        assertEquals(new Round(RoundType.FIVELETTERS, "apple", this.game), this.game.getLastRound());
    }

    @Test
    @DisplayName("Get last round when allrounds is empty will throw error")
    void getLastRoundWhenThereAreNoRounds() {
        assertThrows(RoundException.class, () -> this.game.getLastRound());
    }

    @Test
    @DisplayName("Add score works correctly")
    void addScore() {
        this.game.addScore(500);
        assertEquals(500, this.game.getScore());
    }
}