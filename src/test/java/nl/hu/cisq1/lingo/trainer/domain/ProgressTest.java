package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidHintException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.ABSENT;
import static nl.hu.cisq1.lingo.trainer.domain.Mark.CORRECT;
import static org.junit.jupiter.api.Assertions.*;

class ProgressTest {
    @Test
    @DisplayName("round number should be increased when a new round starts")
    void roundIsIncreased() {
        Progress progress = new Progress();
        assertEquals(0, progress.getCurrentRound());
        progress.nextRound("apple");
        assertEquals(1, progress.getCurrentRound());
    }

    @Test
    @DisplayName("hints should be reset when a new round starts")
    void hintIsReset() {
        Progress progress = new Progress();
        progress.nextRound("apple");
        progress.addHint("ap...");
        progress.addHint("ap..e");
        progress.nextRound("beard");
        assertEquals(1, progress.getHints().size());
    }

    @Test
    @DisplayName("hints should be the first character of the word to guess when a new round starts")
    void hintIsCorrect() {
        Progress progress = new Progress();
        progress.nextRound("apple");
        assertEquals("a....", progress.getHints().get(0));
    }

    @Test
    @DisplayName("score should be increased correctly")
    void scoreIsIncreased() {
        Progress progress = new Progress();
        assertEquals(0, progress.getScore());
        progress.increaseScore(25);
        assertEquals(25, progress.getScore());
    }
}