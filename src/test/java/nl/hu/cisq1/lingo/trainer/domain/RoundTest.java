package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.GameRoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {

    @Test
    public void tryAGuessHappyFlow() {
        Round round = new Round("woord");
        round.tryAGuess("wooof");
        assertEquals(1, round.numOfTriedAttempts());
        assertEquals(3, round.numOfAttemptsLeft());
    }

    @Test
    public void attempsSurpassTriesLimit() {
        Round round = new Round("woord");
        round.tryAGuess("wooof");
        round.tryAGuess("wooof");
        round.tryAGuess("wooof");
        round.tryAGuess("wooof");


        assertEquals(0, round.numOfAttemptsLeft());

        assertThrows(GameRoundException.class,
                () -> round.tryAGuess("wooof"));
    }

    @Test
    public void getFeedbackOnNoAttempt() {

        Round newlyStartedRound = new Round("woord");
        assertThrows(GameRoundException.class,
                () -> newlyStartedRound.getLatestFeedback());
    }



    @Test
    public void getHintTest() {
        Round round = new Round("woord");
        round.tryAGuess("wqqqq");

        Hint hint = round.getHint();



        ArrayList expectedRes = new ArrayList(List.of("w", "o", "-", "-", "-"));

        // Geen idee waarom het niet wordt berekend als een "equal" terwijl ze dezelfde type (ArrayList) en waarde ([w, o, -, -, -]) hebben
        assertEquals(expectedRes, hint.getLettersList());

    }


    @Test
    public void getHintForAnEndedRound() {
        Round round = new Round("woord");
        round.tryAGuess("wooof");
        round.tryAGuess("wooof");
        round.tryAGuess("wooof");
        round.tryAGuess("wooof");


        assertThrows(GameRoundException.class,
                () -> round.getHint());
    }

}