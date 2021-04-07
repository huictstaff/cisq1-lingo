package nl.hu.cisq1.lingo.trainer.application.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.ToString;
import nl.hu.cisq1.lingo.trainer.domain.Hint;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class HintDTO {
    public String guess;
    public List<Character> hint;

    public HintDTO(Hint hint) {
        this.guess = hint.getGuess();
        this.hint = hint.getHint();
    }
}
