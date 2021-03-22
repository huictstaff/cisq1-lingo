package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.CiTestConfiguration;
import nl.hu.cisq1.lingo.trainer.data.GameRepository;
import nl.hu.cisq1.lingo.trainer.domain.LingoGame;
import nl.hu.cisq1.lingo.trainer.exception.InvalidFeedbackException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Import(CiTestConfiguration.class)
class LingoServiceIntegrationTest {
    private LingoGame game;

    @Autowired
    private LingoService lingoService;

    @Autowired
    private GameRepository repository;

    @BeforeEach
    void initalize() {
        this.game = new LingoGame();
        this.game.newRound("appel");
        this.repository.save(game);
    }

    @Test
    @DisplayName("Starting a game succesfully")
    void startGame() {
        assertDoesNotThrow(this.lingoService::startGame);
        assertNotNull(this.lingoService.startGame());
    }

    @Test
    @DisplayName("Making a correct guess")
    void makeCorrectGuess() {
        this.lingoService.makeGuess("appel", this.game.getId());
        assertEquals("appel", this.game.getAllRounds().get(0).getLastHint());
    }

    @Test
    @DisplayName("Throw error when guess length is not the same as the word to guess length")
    void makeWrongLengthGuess() {
        assertThrows(InvalidFeedbackException.class, () -> this.lingoService.makeGuess("monden", this.game.getId()));
    }
}
