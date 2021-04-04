package nl.hu.cisq1.lingo.application;

import nl.hu.cisq1.lingo.CiTestConfiguration;
import nl.hu.cisq1.lingo.domain.Game;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Import(CiTestConfiguration.class)
@Transactional
public class TrainerServiceIntegrationTest {
    @Autowired
    private TrainerService trainerService;

    @Test
    @DisplayName("Starting a new game and doing a guess adds second feedback object in round.")
    void guessAddsToRoundFeedback() {
        Game game = trainerService.startNewGame();
        trainerService.doGuess(game.getId(), "toetj");
        assertEquals(trainerService.getGame(game.getId()).getLastRound().getRoundFeedback().size(), 2);
    }

    @Test
    @DisplayName("New game should not be empty")
    void startingNewGameIsNotEmpty() {
        assertNotNull(this.trainerService.startNewGame());
    }

}
