package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.CiTestConfiguration;
import nl.hu.cisq1.lingo.trainer.application.dto.GameDTO;
import nl.hu.cisq1.lingo.trainer.application.dto.HintDTO;
import nl.hu.cisq1.lingo.trainer.domain.State;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Import(CiTestConfiguration.class)
@ActiveProfiles("ci")
public class GameServiceIntegrationTest {
    @Autowired
    private GameService service;
    private Long id;

    @BeforeEach
    void setUp() {
        GameDTO game = this.service.createGame();
        this.id = game.id;
    }

    @Test
    @DisplayName("creating a game should save it in the database and it should then be accessible on its id")
    void createGameShouldSave() {
        GameDTO createGameDTO = this.service.createGame();
        GameDTO loadGame = this.service.getGame(createGameDTO.id);
        assertEquals(loadGame, createGameDTO);
    }

    @Test
    @DisplayName("guessing on a game should update it")
    void guessGameTest() {
        this.service.guess(this.id, "woord");
        List<HintDTO> hintDTOS = this.service.getGame(this.id).rounds.get(0).hints;
        assertEquals(hintDTOS.get(hintDTOS.size() - 1).guess, "woord");
    }

    @Test
    @DisplayName("when a game is created, its state should be IN_PROGRESS")
    void progressState() {
        GameDTO game = this.service.getGame(this.id);
        assertEquals(State.IN_PROGRESS, game.rounds.get(0).getState());
    }

    @Test
    @DisplayName("guessing correctly should set state to GUESSED")
    void guessCorrect() {
        GameDTO game = this.service.guess(this.id, "pizza");
        assertEquals(State.GUESSED, game.rounds.get(0).state);
    }

    @Test
    @DisplayName("guessing too many times should set state to LOST")
    void guessWrong() {
        this.service.guess(this.id, "paziz");
        this.service.guess(this.id, "paziz");
        this.service.guess(this.id, "paziz");
        this.service.guess(this.id, "paziz");
        GameDTO game = this.service.guess(this.id, "paziz");
        assertEquals(State.LOST, game.rounds.get(0).state);
    }
}
