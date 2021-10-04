package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    @Test
    @DisplayName("something")
    public void connectingFeedbackwithRound(){
        Round test = new Round ("Grond");
        Feedback testFB = test.giveFeedBack("Gangs");
        assertEquals(testFB.toString(),"");
    }
}