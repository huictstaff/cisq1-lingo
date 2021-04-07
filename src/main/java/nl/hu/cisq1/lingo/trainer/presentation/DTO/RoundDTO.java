package nl.hu.cisq1.lingo.trainer.presentation.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.Round;

@Getter
@Setter
@ToString
public class RoundDTO {
    private int guesses = 5;
    private String word;
    private Boolean isWon = false;
    private FeedbackDTO feedback;
    @JsonCreator
    public RoundDTO(){

    }

    public RoundDTO(Round round) {
        this.guesses = round.getGuesses();
        this.word = round.getWord();
        this.isWon = round.getWon();
        this.feedback = new FeedbackDTO(round.getFeedback());
    }
}
