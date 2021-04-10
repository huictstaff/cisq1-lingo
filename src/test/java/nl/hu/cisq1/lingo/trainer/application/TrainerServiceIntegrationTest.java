package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.CiTestConfiguration;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidLengthException;
import nl.hu.cisq1.lingo.trainer.domain.exception.RoundIsOverException;
import nl.hu.cisq1.lingo.trainer.presentation.DTO.GameDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import nl.hu.cisq1.lingo.trainer.domain.Game;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@Transactional
@Import(CiTestConfiguration.class)
class TrainerServiceIntegrationTest {

    @Autowired
    private TrainerService trainerService;

    @AfterEach
    void afterEach() {
        trainerService.setGame(null);
    }

    @Test
    @DisplayName("Creates a game object and starts the first round.")
    void startsGame() {
        GameDTO game = trainerService.startGame();
        assertEquals(1, game.getRounds().size());
    }

    @Test
    void guess() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            trainerService.guess("asdlk");
        });
        trainerService.startGame();
        trainerService.guess(trainerService.getGame().getLastRound().getWord());
        assertTrue(trainerService.getGame().getLastRound().getWon());
        assertThrows(RoundIsOverException.class, () -> trainerService.guess("woord"));
        trainerService.startGame();
        assertThrows(InvalidLengthException.class, () -> trainerService.guess("thisistoolong"));
    }

    @Test
    void gameSaved() {
        trainerService.startGame();
        trainerService.guess("asklf");
        trainerService.saveGame();
        assertEquals(Optional.of(trainerService.getGame()).toString(),
                trainerService.findGame(Long.parseLong("" + trainerService.getGame().getId())).toString());
        trainerService.deleteGameById(trainerService.getGame().getId());
    }
}