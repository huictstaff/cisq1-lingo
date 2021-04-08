package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.CiTestConfiguration;
import nl.hu.cisq1.lingo.trainer.presentation.dto.GameStatus;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

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

//    @Test
//    @DisplayName("provides random 5, 6 and 7 letter words")
//    void providesRandomWord() throws Exception {
//        for (int wordLength = 5; wordLength <= 7; wordLength++) {
//            GameStatus randomWord = this.service.getStatus(Long.MAX_VALUE);
//            assertEquals(wordLength, randomWord.length());
//
//            // Printing is not necessary in most tests
//            // (done here for verification of student configuration)
//            System.out.println("Random word: " + randomWord);
//        }
//        System.out.println("a");
//    }
}
