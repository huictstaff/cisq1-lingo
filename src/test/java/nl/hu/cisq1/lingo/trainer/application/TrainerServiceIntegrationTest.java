package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.CiTestConfiguration;
import nl.hu.cisq1.lingo.trainer.presentation.dto.GameStatus;
import nl.hu.cisq1.lingo.trainer.presentation.dto.Guess;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This integration test integrates between the service layer,
 * the data layer and the framework.
 * In a dev environment, we test against the actual database.
 *
 * In continuous integration pipelines, we should not
 * use the actual database as we don't have one.
 * We want to replace it with an in-memory database.
 *
 * Set the profile to CI, so that application-ci.properties is loaded
 * and an import script is run.
 **/
@SpringBootTest
@Import(CiTestConfiguration.class)
class TrainerServiceIntegrationTest {

    @Autowired
    private TrainerService service;

    @ParameterizedTest
    @DisplayName("provides random 5, 6 and 7 letter words")
    @MethodSource("wordsWithIDs")
    void providesGameStatus(Long id, String word) throws Exception {
        GameStatus gameStatus = this.service.getStatus(id);
        assertEquals(id, gameStatus.getId());
    }

    @ParameterizedTest
    @DisplayName("provides random 5, 6 and 7 letter words")
    @MethodSource("wordsWithIDs")
    void guessWordRight(Long id, String word) throws Exception {
        Guess guess = new Guess();
        guess.guess = word;

        GameStatus gameStatus = this.service.guess(id, guess);
        assertEquals(CORRECT, gameStatus.getLastFeedback().totalMark());
    }

    static Stream<Arguments> wordsWithIDs() {
        return Stream.of(
                Arguments.of(1L, "pizza"),
                Arguments.of(2L, "oranje"),
                Arguments.of(3L, "wanorde")
        );
    }
}
