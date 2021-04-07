package nl.hu.cisq1.lingo.trainer.presentation.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.hu.cisq1.lingo.trainer.domain.Hint;
import nl.hu.cisq1.lingo.trainer.domain.Mark;

import java.util.List;

@Getter
@Setter
@ToString
public class HintDTO {
    private List<String> hintStrings;
    private List<Mark> marks;

    @JsonCreator
    public HintDTO() {
    }

    public HintDTO(Hint hint) {
        this.hintStrings = hint.getHintStrings();
        this.marks = hint.getMarks();
    }
}

