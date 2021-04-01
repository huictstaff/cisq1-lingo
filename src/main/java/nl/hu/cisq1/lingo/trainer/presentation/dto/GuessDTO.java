package nl.hu.cisq1.lingo.trainer.presentation.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class GuessDTO {
    public String guess;

    @JsonCreator
    public GuessDTO(String guess) {
        this.guess = guess;
    }
}
