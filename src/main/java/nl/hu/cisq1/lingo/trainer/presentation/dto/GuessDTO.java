package nl.hu.cisq1.lingo.trainer.presentation.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class GuessDTO {
    public String guess;

    @JsonCreator
    @NotBlank
    @NotEmpty
    @Size(max = 7, min = 5)
    public GuessDTO(String guess) {
        this.guess = guess;
    }
}
