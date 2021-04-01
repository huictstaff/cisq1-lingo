package nl.hu.cisq1.lingo.trainer.application.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;
import nl.hu.cisq1.lingo.trainer.domain.Hint;

import java.util.List;

@Getter
@AllArgsConstructor
public class HintDTO {
    public String guess;
    public List<Character> hint;

    public HintDTO(Hint hint) {
        this.guess = hint.getGuess();
        this.hint = hint.getHint();
    }
}
